package com.harghar.library.rarebookservice.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.harghar.library.rarebookservice.entity.RareBookRequestStatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RareBookRequestResponse {
    Long id;
    UUID requesterId;
    String bookTitle;
    String authorName;
    Double requestedLatitude;
    Double requestedLongitude;
    BigDecimal maxBudget;
    RareBookRequestStatus status;
    Instant createdAt;
}
