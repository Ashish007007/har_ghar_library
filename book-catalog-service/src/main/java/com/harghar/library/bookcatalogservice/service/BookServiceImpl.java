package com.harghar.library.bookcatalogservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harghar.library.bookcatalogservice.dto.BookResponse;
import com.harghar.library.bookcatalogservice.dto.CreateBookRequest;
import com.harghar.library.bookcatalogservice.dto.UpdateBookRequest;
import com.harghar.library.bookcatalogservice.entity.Book;
import com.harghar.library.bookcatalogservice.exception.DuplicateResourceException;
import com.harghar.library.bookcatalogservice.exception.ResourceNotFoundException;
import com.harghar.library.bookcatalogservice.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookResponse createBook(CreateBookRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("Book already exists with ISBN: " + request.getIsbn());
        }

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .category(request.getCategory())
                .condition(request.getCondition())
                .rentalPricePerDay(request.getRentalPricePerDay())
                .isFreeSharing(request.getIsFreeSharing())
                .isAvailable(request.getIsAvailable())
                .ownerId(request.getOwnerId())
                .build();

        return mapToResponse(bookRepository.save(book));
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        return mapToResponse(findBookById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByOwnerId(UUID ownerId) {
        return bookRepository.findAllByOwnerId(ownerId).stream().map(this::mapToResponse).toList();
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        Book book = findBookById(id);

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getIsbn() != null && !request.getIsbn().equals(book.getIsbn()) && bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("Another book already exists with ISBN: " + request.getIsbn());
        }
        if (request.getIsbn() != null) {
            book.setIsbn(request.getIsbn());
        }
        if (request.getCategory() != null) {
            book.setCategory(request.getCategory());
        }
        if (request.getCondition() != null) {
            book.setCondition(request.getCondition());
        }
        if (request.getRentalPricePerDay() != null) {
            book.setRentalPricePerDay(request.getRentalPricePerDay());
        }
        if (request.getIsFreeSharing() != null) {
            book.setIsFreeSharing(request.getIsFreeSharing());
        }
        if (request.getIsAvailable() != null) {
            book.setIsAvailable(request.getIsAvailable());
        }

        return mapToResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookResponse updateBookAvailability(Long id, boolean isAvailable) {
        Book book = findBookById(id);
        book.setIsAvailable(isAvailable);
        return mapToResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    private BookResponse mapToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .category(book.getCategory())
                .condition(book.getCondition())
                .rentalPricePerDay(book.getRentalPricePerDay())
                .isFreeSharing(book.getIsFreeSharing())
                .isAvailable(book.getIsAvailable())
                .ownerId(book.getOwnerId())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
