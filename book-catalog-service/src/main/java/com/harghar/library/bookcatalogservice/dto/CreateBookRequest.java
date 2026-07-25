package com.harghar.library.bookcatalogservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.harghar.library.bookcatalogservice.entity.BookCondition;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookRequest {

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 150)
    private String author;

    @NotBlank
    @Size(max = 20)
    private String isbn;

    @NotBlank
    @Size(max = 100)
    private String category;

    @NotNull
    private BookCondition condition;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal rentalPricePerDay;

    @NotNull
    private Boolean isFreeSharing;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    private UUID ownerId;
}
