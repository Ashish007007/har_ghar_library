package com.harghar.library.bookcatalogservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookAvailabilityRequest {

    @NotNull
    private Boolean isAvailable;
}
