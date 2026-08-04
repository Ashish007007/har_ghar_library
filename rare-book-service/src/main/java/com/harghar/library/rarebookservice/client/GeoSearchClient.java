package com.harghar.library.rarebookservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harghar.library.rarebookservice.config.FeignClientConfig;

@FeignClient(name = "geo-search-service", configuration = FeignClientConfig.class)
public interface GeoSearchClient {

    @GetMapping("/api/v1/search/nearby")
    List<Long> findNearbyBookIds(
            @RequestParam("lat") double latitude,
            @RequestParam("lng") double longitude,
            @RequestParam("radiusKm") double radiusKm);
}
