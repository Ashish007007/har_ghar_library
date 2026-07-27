package com.harghar.library.rentalorderservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harghar.library.rentalorderservice.client.BookCatalogClient;
import com.harghar.library.rentalorderservice.client.dto.BookCatalogBookResponse;
import com.harghar.library.rentalorderservice.dto.CreateRentalOrderRequest;
import com.harghar.library.rentalorderservice.dto.RentalOrderResponse;
import com.harghar.library.rentalorderservice.entity.RentalOrder;
import com.harghar.library.rentalorderservice.entity.RentalOrderStatus;
import com.harghar.library.rentalorderservice.exception.InvalidOrderStateException;
import com.harghar.library.rentalorderservice.exception.ResourceNotFoundException;
import com.harghar.library.rentalorderservice.exception.UnauthorizedActionException;
import com.harghar.library.rentalorderservice.repository.RentalOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalOrderServiceImpl implements RentalOrderService {

    private final RentalOrderRepository rentalOrderRepository;
    private final BookCatalogClient bookCatalogClient;

    @Override
    @Transactional
    public RentalOrderResponse requestRentalOrder(CreateRentalOrderRequest request) {
        BookCatalogBookResponse book = bookCatalogClient.getBookById(request.getBookId());
        if (book == null || book.getId() == null) {
            throw new ResourceNotFoundException("Book not found with id: " + request.getBookId());
        }
        if (!Boolean.TRUE.equals(book.getIsAvailable())) {
            throw new InvalidOrderStateException("Book is currently not available for rental");
        }
        if (book.getRentalPricePerDay() == null) {
            throw new InvalidOrderStateException("Book rental price is not available");
        }
        if (book.getOwnerId() == null) {
            throw new InvalidOrderStateException("Book owner information is missing");
        }

        long rentalDays = Math.max(1, ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()));
        BigDecimal totalFee = book.getRentalPricePerDay().multiply(BigDecimal.valueOf(rentalDays));

        RentalOrder order = RentalOrder.builder()
                .bookId(request.getBookId())
                .borrowerId(request.getBorrowerId())
                .lenderId(book.getOwnerId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .totalRentalFee(totalFee)
                .status(RentalOrderStatus.REQUESTED)
                .build();

        return mapToResponse(rentalOrderRepository.save(order));
    }

    @Override
    @Transactional
    public RentalOrderResponse approveOrder(Long orderId, UUID lenderId) {
        RentalOrder order = findById(orderId);
        assertLender(order, lenderId);
        assertStatus(order, RentalOrderStatus.REQUESTED, "Only REQUESTED orders can be approved");
        order.setStatus(RentalOrderStatus.APPROVED);
        return mapToResponse(rentalOrderRepository.save(order));
    }

    @Override
    @Transactional
    public RentalOrderResponse rejectOrder(Long orderId, UUID lenderId) {
        RentalOrder order = findById(orderId);
        assertLender(order, lenderId);
        assertStatus(order, RentalOrderStatus.REQUESTED, "Only REQUESTED orders can be rejected");
        order.setStatus(RentalOrderStatus.REJECTED);
        return mapToResponse(rentalOrderRepository.save(order));
    }

    @Override
    @Transactional
    public RentalOrderResponse markAsPickedUp(Long orderId, UUID lenderId) {
        RentalOrder order = findById(orderId);
        assertLender(order, lenderId);
        assertStatus(order, RentalOrderStatus.APPROVED, "Only APPROVED orders can be marked as picked up");
        order.setStatus(RentalOrderStatus.PICKED_UP);
        return mapToResponse(rentalOrderRepository.save(order));
    }

    @Override
    @Transactional
    public RentalOrderResponse completeReturn(Long orderId, UUID lenderId) {
        RentalOrder order = findById(orderId);
        assertLender(order, lenderId);
        if (order.getStatus() != RentalOrderStatus.PICKED_UP && order.getStatus() != RentalOrderStatus.OVERDUE) {
            throw new InvalidOrderStateException("Only PICKED_UP or OVERDUE orders can be returned");
        }
        order.setReturnDate(LocalDate.now());
        order.setStatus(RentalOrderStatus.RETURNED);
        return mapToResponse(rentalOrderRepository.save(order));
    }

    private RentalOrder findById(Long id) {
        return rentalOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental order not found with id: " + id));
    }

    private void assertLender(RentalOrder order, UUID lenderId) {
        if (!order.getLenderId().equals(lenderId)) {
            throw new UnauthorizedActionException("Only the lender can perform this action");
        }
    }

    private void assertStatus(RentalOrder order, RentalOrderStatus expected, String message) {
        if (order.getStatus() != expected) {
            throw new InvalidOrderStateException(message);
        }
    }

    private RentalOrderResponse mapToResponse(RentalOrder order) {
        return RentalOrderResponse.builder()
                .id(order.getId())
                .bookId(order.getBookId())
                .borrowerId(order.getBorrowerId())
                .lenderId(order.getLenderId())
                .startDate(order.getStartDate())
                .endDate(order.getEndDate())
                .returnDate(order.getReturnDate())
                .totalRentalFee(order.getTotalRentalFee())
                .status(order.getStatus())
                .build();
    }
}
