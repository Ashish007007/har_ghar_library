package com.harghar.library.rentalorderservice.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harghar.library.rentalorderservice.dto.CreateRentalOrderRequest;
import com.harghar.library.rentalorderservice.dto.RentalOrderResponse;
import com.harghar.library.rentalorderservice.service.RentalOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rental-orders")
@RequiredArgsConstructor
public class RentalOrderController {

    private final RentalOrderService rentalOrderService;

    @PostMapping("/request")
    public ResponseEntity<RentalOrderResponse> requestRentalOrder(@Valid @RequestBody CreateRentalOrderRequest request) {
        RentalOrderResponse response = rentalOrderService.requestRentalOrder(request);
        return ResponseEntity.created(URI.create("/api/v1/rental-orders/" + response.getId())).body(response);
    }

    @PatchMapping("/{orderId}/approve")
    public ResponseEntity<RentalOrderResponse> approveOrder(
            @PathVariable Long orderId,
            @RequestParam UUID lenderId) {
        return ResponseEntity.ok(rentalOrderService.approveOrder(orderId, lenderId));
    }

    @PatchMapping("/{orderId}/reject")
    public ResponseEntity<RentalOrderResponse> rejectOrder(
            @PathVariable Long orderId,
            @RequestParam UUID lenderId) {
        return ResponseEntity.ok(rentalOrderService.rejectOrder(orderId, lenderId));
    }

    @PatchMapping("/{orderId}/picked-up")
    public ResponseEntity<RentalOrderResponse> markAsPickedUp(
            @PathVariable Long orderId,
            @RequestParam UUID lenderId) {
        return ResponseEntity.ok(rentalOrderService.markAsPickedUp(orderId, lenderId));
    }

    @PatchMapping("/{orderId}/return")
    public ResponseEntity<RentalOrderResponse> completeReturn(
            @PathVariable Long orderId,
            @RequestParam UUID lenderId) {
        return ResponseEntity.ok(rentalOrderService.completeReturn(orderId, lenderId));
    }
}
