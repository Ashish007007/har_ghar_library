package com.harghar.library.rarebookservice.service;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harghar.library.rarebookservice.client.BookCatalogClient;
import com.harghar.library.rarebookservice.client.GeoSearchClient;
import com.harghar.library.rarebookservice.client.dto.BookCatalogBookResponse;
import com.harghar.library.rarebookservice.dto.CollectorCandidateResponse;
import com.harghar.library.rarebookservice.dto.CreateRareBookRequest;
import com.harghar.library.rarebookservice.dto.RareBookRequestResponse;
import com.harghar.library.rarebookservice.entity.RareBookRequest;
import com.harghar.library.rarebookservice.entity.RareBookRequestStatus;
import com.harghar.library.rarebookservice.exception.ResourceNotFoundException;
import com.harghar.library.rarebookservice.repository.RareBookRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RareBookRequestServiceImpl implements RareBookRequestService {

    private final RareBookRequestRepository rareBookRequestRepository;
    private final BookCatalogClient bookCatalogClient;
    private final GeoSearchClient geoSearchClient;

    @Override
    @Transactional
    public RareBookRequestResponse createRequest(CreateRareBookRequest request) {
        RareBookRequest entity = RareBookRequest.builder()
                .requesterId(request.getRequesterId())
                .bookTitle(request.getBookTitle())
                .authorName(request.getAuthorName())
                .requestedLatitude(request.getRequestedLatitude())
                .requestedLongitude(request.getRequestedLongitude())
                .maxBudget(request.getMaxBudget())
                .status(RareBookRequestStatus.ACTIVE)
                .build();
        return mapToResponse(rareBookRequestRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public CollectorCandidateResponse findCollectorCandidates(Long requestId, double radiusKm) {
        RareBookRequest request = rareBookRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Rare book request not found with id: " + requestId));

        List<Long> nearbyBookIds = geoSearchClient.findNearbyBookIds(
                request.getRequestedLatitude(),
                request.getRequestedLongitude(),
                radiusKm);

        Set<Long> nearbySet = nearbyBookIds.stream().collect(Collectors.toSet());
        String requestedTitle = normalize(request.getBookTitle());
        String requestedAuthor = normalize(request.getAuthorName());

        Set<UUID> collectorIds = bookCatalogClient.getAllBooks().stream()
                .filter(book -> nearbySet.contains(book.getId()))
                .filter(book -> Boolean.TRUE.equals(book.getIsAvailable()))
                .filter(book -> book.getRentalPricePerDay() != null
                        && book.getRentalPricePerDay().compareTo(request.getMaxBudget()) <= 0)
                .filter(book -> matches(book.getTitle(), requestedTitle) || matches(book.getAuthor(), requestedAuthor))
                .map(BookCatalogBookResponse::getOwnerId)
                .filter(ownerId -> ownerId != null && !ownerId.equals(request.getRequesterId()))
                .collect(Collectors.toSet());

        return CollectorCandidateResponse.builder()
                .requestId(requestId)
                .collectorIds(collectorIds)
                .build();
    }

    private boolean matches(String source, String target) {
        String normalized = normalize(source);
        return !normalized.isBlank() && normalized.contains(target);
    }

    private String normalize(String value) {
        if (value == null) {
            return "";
        }
        return value.trim().toLowerCase(Locale.ROOT);
    }

    private RareBookRequestResponse mapToResponse(RareBookRequest request) {
        return RareBookRequestResponse.builder()
                .id(request.getId())
                .requesterId(request.getRequesterId())
                .bookTitle(request.getBookTitle())
                .authorName(request.getAuthorName())
                .requestedLatitude(request.getRequestedLatitude())
                .requestedLongitude(request.getRequestedLongitude())
                .maxBudget(request.getMaxBudget())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .build();
    }
}
