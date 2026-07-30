package com.harghar.library.rarebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harghar.library.rarebookservice.entity.RareBookRequest;

public interface RareBookRequestRepository extends JpaRepository<RareBookRequest, Long> {
}
