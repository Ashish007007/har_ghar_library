package com.harghar.library.userservice.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserProfileResponse {
    Long id;
    String email;
    String fullName;
    String phoneNumber;
    String address;
    Double latitude;
    Double longitude;
    Double averageRating;
    Instant createdAt;
}
