package com.harghar.library.geosearchservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.harghar.library.geosearchservice.entity.BookGeoLocation;

public interface BookGeoLocationRepository extends JpaRepository<BookGeoLocation, Long> {

    @Query(value = """
            SELECT bgl.book_id
            FROM book_geo_locations bgl
            WHERE bgl.is_available = true
              AND ST_DWithin(
                  bgl.location::geography,
                  ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)::geography,
                  :radiusMeters
              )
            """, nativeQuery = true)
    List<Long> findAvailableBookIdsWithinRadius(
            @Param("lat") double latitude,
            @Param("lng") double longitude,
            @Param("radiusMeters") double radiusMeters);
}
