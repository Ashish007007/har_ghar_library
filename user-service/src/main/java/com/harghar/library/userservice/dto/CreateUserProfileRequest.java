package com.harghar.library.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserProfileRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 150)
    private String fullName;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 300)
    private String address;

    private Double latitude;

    private Double longitude;
}
