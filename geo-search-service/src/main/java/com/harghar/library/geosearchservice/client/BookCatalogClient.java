package com.harghar.library.geosearchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.harghar.library.geosearchservice.client.dto.BookCatalogBookResponse;
import com.harghar.library.geosearchservice.config.FeignClientConfig;

@FeignClient(name = "book-catalog-service", configuration = FeignClientConfig.class)
public interface BookCatalogClient {

    @GetMapping("/api/books/{id}")
    BookCatalogBookResponse getBookById(@PathVariable("id") Long bookId);
}
