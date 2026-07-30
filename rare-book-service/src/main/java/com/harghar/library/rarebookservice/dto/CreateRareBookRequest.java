package com.harghar.library.rarebookservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRareBookRequest {

    @NotNull
    private UUID requesterId;

    @NotBlank
    @Size(max = 255)
    private String bookTitle;

    @NotBlank
    @Size(max = 150)
    private String authorName;

    @NotNull
    private Double requestedLatitude;

    @NotNull
    private Double requestedLongitude;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal maxBudget;
}
