package com.harghar.library.chatservice.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {

    @NotNull
    private Long rentalOrderId;

    @NotNull
    private UUID senderId;

    @NotNull
    private UUID receiverId;

    @NotBlank
    private String messageContent;
}
