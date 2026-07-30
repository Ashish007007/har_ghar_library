package com.harghar.library.rarebookservice.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harghar.library.rarebookservice.dto.CollectorCandidateResponse;
import com.harghar.library.rarebookservice.dto.CreateRareBookRequest;
import com.harghar.library.rarebookservice.dto.RareBookRequestResponse;
import com.harghar.library.rarebookservice.service.RareBookRequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rare-book-requests")
@RequiredArgsConstructor
public class RareBookRequestController {

    private final RareBookRequestService rareBookRequestService;

    @PostMapping
    public ResponseEntity<RareBookRequestResponse> createRequest(@Valid @RequestBody CreateRareBookRequest request) {
        RareBookRequestResponse response = rareBookRequestService.createRequest(request);
        return ResponseEntity.created(URI.create("/api/v1/rare-book-requests/" + response.getId())).body(response);
    }

    @GetMapping("/{requestId}/collector-candidates")
    public ResponseEntity<CollectorCandidateResponse> findCollectorCandidates(
            @PathVariable Long requestId,
            @RequestParam(defaultValue = "5") double radiusKm) {
        return ResponseEntity.ok(rareBookRequestService.findCollectorCandidates(requestId, radiusKm));
    }
}
