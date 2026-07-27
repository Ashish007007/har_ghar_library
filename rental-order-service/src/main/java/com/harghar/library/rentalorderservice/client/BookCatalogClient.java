package com.harghar.library.rentalorderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.harghar.library.rentalorderservice.client.dto.BookCatalogBookResponse;

@FeignClient(name = "book-catalog-service")
public interface BookCatalogClient {

    @GetMapping("/api/books/{id}")
    BookCatalogBookResponse getBookById(@PathVariable("id") Long bookId);
}
