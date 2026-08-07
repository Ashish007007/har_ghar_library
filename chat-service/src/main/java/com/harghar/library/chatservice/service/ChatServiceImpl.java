package com.harghar.library.chatservice.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harghar.library.chatservice.dto.ChatMessageRequest;
import com.harghar.library.chatservice.dto.ChatMessageResponse;
import com.harghar.library.chatservice.entity.ChatMessage;
import com.harghar.library.chatservice.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    @Transactional
    public ChatMessageResponse saveMessage(ChatMessageRequest request) {
        ChatMessage message = ChatMessage.builder()
                .rentalOrderId(request.getRentalOrderId())
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .messageContent(request.getMessageContent().trim())
                .sentAt(Instant.now())
                .isRead(false)
                .build();
        return toResponse(chatMessageRepository.save(message));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessageResponse> getHistory(Long rentalOrderId) {
        return chatMessageRepository.findByRentalOrderIdOrderBySentAtAsc(rentalOrderId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ChatMessageResponse toResponse(ChatMessage message) {
        return ChatMessageResponse.builder()
                .id(message.getId())
                .rentalOrderId(message.getRentalOrderId())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .messageContent(message.getMessageContent())
                .sentAt(message.getSentAt())
                .isRead(message.isRead())
                .build();
    }
}
