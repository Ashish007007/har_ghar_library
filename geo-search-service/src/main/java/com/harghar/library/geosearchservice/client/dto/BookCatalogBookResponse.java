package com.harghar.library.geosearchservice.client.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCatalogBookResponse {
    private Long id;
    private String title;
    private String author;
    private BigDecimal rentalPricePerDay;
    private Boolean isAvailable;
    private UUID ownerId;
}
