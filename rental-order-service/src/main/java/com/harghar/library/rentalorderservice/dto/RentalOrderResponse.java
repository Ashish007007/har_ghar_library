package com.harghar.library.rentalorderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.harghar.library.rentalorderservice.entity.RentalOrderStatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RentalOrderResponse {
    Long id;
    Long bookId;
    UUID borrowerId;
    UUID lenderId;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate returnDate;
    BigDecimal totalRentalFee;
    RentalOrderStatus status;
}
