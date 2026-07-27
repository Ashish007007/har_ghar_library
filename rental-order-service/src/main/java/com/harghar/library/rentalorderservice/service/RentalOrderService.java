package com.harghar.library.rentalorderservice.service;

import java.util.UUID;

import com.harghar.library.rentalorderservice.dto.CreateRentalOrderRequest;
import com.harghar.library.rentalorderservice.dto.RentalOrderResponse;

public interface RentalOrderService {

    RentalOrderResponse requestRentalOrder(CreateRentalOrderRequest request);

    RentalOrderResponse approveOrder(Long orderId, UUID lenderId);

    RentalOrderResponse rejectOrder(Long orderId, UUID lenderId);

    RentalOrderResponse markAsPickedUp(Long orderId, UUID lenderId);

    RentalOrderResponse completeReturn(Long orderId, UUID lenderId);
}
