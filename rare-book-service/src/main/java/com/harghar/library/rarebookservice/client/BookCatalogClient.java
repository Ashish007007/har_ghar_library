package com.harghar.library.rarebookservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.harghar.library.rarebookservice.client.dto.BookCatalogBookResponse;

@FeignClient(name = "book-catalog-service")
public interface BookCatalogClient {

    @GetMapping("/api/books")
    List<BookCatalogBookResponse> getAllBooks();
}
