package com.harghar.library.bookcatalogservice.dto;

import java.math.BigDecimal;

import com.harghar.library.bookcatalogservice.entity.BookCondition;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookRequest {

    @Size(max = 255)
    private String title;

    @Size(max = 150)
    private String author;

    @Size(max = 20)
    private String isbn;

    @Size(max = 100)
    private String category;

    private BookCondition condition;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal rentalPricePerDay;

    private Boolean isFreeSharing;

    private Boolean isAvailable;
}
