package com.harghar.library.geosearchservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harghar.library.geosearchservice.service.GeoSearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class GeoSearchController {

    private final GeoSearchService geoSearchService;

    @GetMapping("/nearby")
    public ResponseEntity<List<Long>> findNearbyBooks(
            @RequestParam("lat") double latitude,
            @RequestParam("lng") double longitude,
            @RequestParam("radiusKm") double radiusKm) {
        return ResponseEntity.ok(geoSearchService.findNearbyAvailableBookIds(latitude, longitude, radiusKm));
    }
}
