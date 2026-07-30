package com.harghar.library.rarebookservice.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CollectorCandidateResponse {
    Long requestId;
    Set<UUID> collectorIds;
}
