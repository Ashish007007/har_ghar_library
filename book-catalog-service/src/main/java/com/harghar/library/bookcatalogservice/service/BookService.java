package com.harghar.library.bookcatalogservice.service;

import java.util.List;
import java.util.UUID;

import com.harghar.library.bookcatalogservice.dto.BookResponse;
import com.harghar.library.bookcatalogservice.dto.CreateBookRequest;
import com.harghar.library.bookcatalogservice.dto.UpdateBookRequest;

public interface BookService {

    BookResponse createBook(CreateBookRequest request);

    BookResponse getBookById(Long id);

    List<BookResponse> getAllBooks();

    List<BookResponse> getBooksByOwnerId(UUID ownerId);

    BookResponse updateBook(Long id, UpdateBookRequest request);

    BookResponse updateBookAvailability(Long id, boolean isAvailable);

    void deleteBook(Long id);
}
