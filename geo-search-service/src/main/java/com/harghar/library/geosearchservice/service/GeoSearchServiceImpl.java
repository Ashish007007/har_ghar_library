package com.harghar.library.geosearchservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harghar.library.geosearchservice.repository.BookGeoLocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeoSearchServiceImpl implements GeoSearchService {

    private final BookGeoLocationRepository bookGeoLocationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Long> findNearbyAvailableBookIds(double latitude, double longitude, double radiusKm) {
        double radiusMeters = radiusKm * 1000.0;
        return bookGeoLocationRepository.findAvailableBookIdsWithinRadius(latitude, longitude, radiusMeters);
    }
}
