package com.harghar.library.userservice.service;

import java.util.List;

import com.harghar.library.userservice.dto.CreateUserProfileRequest;
import com.harghar.library.userservice.dto.UpdateUserProfileRequest;
import com.harghar.library.userservice.dto.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse createUserProfile(CreateUserProfileRequest request);

    UserProfileResponse getUserProfileById(Long id);

    List<UserProfileResponse> getAllUserProfiles();

    UserProfileResponse updateUserProfile(Long id, UpdateUserProfileRequest request);

    void deleteUserProfile(Long id);
}
