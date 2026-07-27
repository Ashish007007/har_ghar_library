package com.harghar.library.rentalorderservice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rental_orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID borrowerId;

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID lenderId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private LocalDate returnDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalRentalFee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RentalOrderStatus status;
}
