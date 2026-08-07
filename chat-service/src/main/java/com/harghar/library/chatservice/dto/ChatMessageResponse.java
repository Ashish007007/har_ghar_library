package com.harghar.library.chatservice.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChatMessageResponse {
    Long id;
    Long rentalOrderId;
    UUID senderId;
    UUID receiverId;
    String messageContent;
    Instant sentAt;
    boolean isRead;
}
