package com.harghar.library.geosearchservice.service;

import java.util.List;

public interface GeoSearchService {

    List<Long> findNearbyAvailableBookIds(double latitude, double longitude, double radiusKm);
}
