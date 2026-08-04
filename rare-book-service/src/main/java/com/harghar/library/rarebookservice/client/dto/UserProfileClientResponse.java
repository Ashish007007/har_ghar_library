package com.harghar.library.rarebookservice.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileClientResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
}
