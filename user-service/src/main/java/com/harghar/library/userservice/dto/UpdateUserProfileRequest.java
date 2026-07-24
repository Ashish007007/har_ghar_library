package com.harghar.library.userservice.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileRequest {

    @Size(max = 150)
    private String fullName;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 300)
    private String address;

    private Double latitude;

    private Double longitude;

    private Double averageRating;
}
