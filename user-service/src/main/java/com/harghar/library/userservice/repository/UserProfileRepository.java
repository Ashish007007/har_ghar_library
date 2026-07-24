package com.harghar.library.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harghar.library.userservice.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    boolean existsByEmail(String email);

    Optional<UserProfile> findByEmail(String email);
}
