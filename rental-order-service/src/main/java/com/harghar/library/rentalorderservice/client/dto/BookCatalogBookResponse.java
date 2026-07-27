package com.harghar.library.rentalorderservice.client.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCatalogBookResponse {
    private Long id;
    private Boolean isAvailable;
    private BigDecimal rentalPricePerDay;
    private UUID ownerId;
}
