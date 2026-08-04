package com.harghar.library.geosearchservice.client.dto;

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
