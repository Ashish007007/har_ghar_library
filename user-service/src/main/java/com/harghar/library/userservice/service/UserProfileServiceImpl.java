package com.harghar.library.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harghar.library.userservice.dto.CreateUserProfileRequest;
import com.harghar.library.userservice.dto.UpdateUserProfileRequest;
import com.harghar.library.userservice.dto.UserProfileResponse;
import com.harghar.library.userservice.entity.UserProfile;
import com.harghar.library.userservice.exception.DuplicateResourceException;
import com.harghar.library.userservice.exception.ResourceNotFoundException;
import com.harghar.library.userservice.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public UserProfileResponse createUserProfile(CreateUserProfileRequest request) {
        if (userProfileRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User profile already exists for email: " + request.getEmail());
        }

        UserProfile userProfile = UserProfile.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        return mapToResponse(userProfileRepository.save(userProfile));
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfileById(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + id));
        return mapToResponse(userProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfileResponse> getAllUserProfiles() {
        return userProfileRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public UserProfileResponse updateUserProfile(Long id, UpdateUserProfileRequest request) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found with id: " + id));

        if (request.getFullName() != null) {
            userProfile.setFullName(request.getFullName());
        }
        if (request.getPhoneNumber() != null) {
            userProfile.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null) {
            userProfile.setAddress(request.getAddress());
        }
        if (request.getLatitude() != null) {
            userProfile.setLatitude(request.getLatitude());
        }
        if (request.getLongitude() != null) {
            userProfile.setLongitude(request.getLongitude());
        }
        if (request.getAverageRating() != null) {
            userProfile.setAverageRating(request.getAverageRating());
        }

        return mapToResponse(userProfileRepository.save(userProfile));
    }

    @Override
    @Transactional
    public void deleteUserProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("User profile not found with id: " + id);
        }
        userProfileRepository.deleteById(id);
    }

    private UserProfileResponse mapToResponse(UserProfile userProfile) {
        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .email(userProfile.getEmail())
                .fullName(userProfile.getFullName())
                .phoneNumber(userProfile.getPhoneNumber())
                .address(userProfile.getAddress())
                .latitude(userProfile.getLatitude())
                .longitude(userProfile.getLongitude())
                .averageRating(userProfile.getAverageRating())
                .createdAt(userProfile.getCreatedAt())
                .build();
    }
}
