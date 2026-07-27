package com.harghar.library.rentalorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harghar.library.rentalorderservice.entity.RentalOrder;

public interface RentalOrderRepository extends JpaRepository<RentalOrder, Long> {
}
