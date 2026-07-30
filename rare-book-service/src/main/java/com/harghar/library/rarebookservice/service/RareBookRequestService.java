package com.harghar.library.rarebookservice.service;

import com.harghar.library.rarebookservice.dto.CollectorCandidateResponse;
import com.harghar.library.rarebookservice.dto.CreateRareBookRequest;
import com.harghar.library.rarebookservice.dto.RareBookRequestResponse;

public interface RareBookRequestService {

    RareBookRequestResponse createRequest(CreateRareBookRequest request);

    CollectorCandidateResponse findCollectorCandidates(Long requestId, double radiusKm);
}
