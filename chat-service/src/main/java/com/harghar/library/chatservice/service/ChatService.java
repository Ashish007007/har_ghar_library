package com.harghar.library.chatservice.service;

import java.util.List;

import com.harghar.library.chatservice.dto.ChatMessageRequest;
import com.harghar.library.chatservice.dto.ChatMessageResponse;

public interface ChatService {
    ChatMessageResponse saveMessage(ChatMessageRequest request);

    List<ChatMessageResponse> getHistory(Long rentalOrderId);
}
