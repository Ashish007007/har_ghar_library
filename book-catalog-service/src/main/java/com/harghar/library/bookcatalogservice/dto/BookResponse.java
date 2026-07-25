package com.harghar.library.bookcatalogservice.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.harghar.library.bookcatalogservice.entity.BookCondition;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookResponse {
    Long id;
    String title;
    String author;
    String isbn;
    String category;
    BookCondition condition;
    BigDecimal rentalPricePerDay;
    Boolean isFreeSharing;
    Boolean isAvailable;
    UUID ownerId;
    Instant createdAt;
}
