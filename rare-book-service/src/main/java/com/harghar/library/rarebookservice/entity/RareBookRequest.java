package com.harghar.library.rarebookservice.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rare_book_requests")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RareBookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID requesterId;

    @Column(nullable = false, length = 255)
    private String bookTitle;

    @Column(nullable = false, length = 150)
    private String authorName;

    @Column(nullable = false)
    private Double requestedLatitude;

    @Column(nullable = false)
    private Double requestedLongitude;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal maxBudget;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RareBookRequestStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        if (status == null) {
            status = RareBookRequestStatus.ACTIVE;
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
