package com.harghar.library.chatservice.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harghar.library.chatservice.dto.ChatMessageRequest;
import com.harghar.library.chatservice.dto.ChatMessageResponse;
import com.harghar.library.chatservice.service.ChatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * STOMP clients publish to /app/chat.send and subscribe to
     * /topic/chat/{rentalOrderId}. The recipient may additionally subscribe to
     * /user/queue/messages when the application supplies an authenticated principal.
     */
    @MessageMapping("/chat.send")
    public void sendMessage(@Valid ChatMessageRequest request) {
        ChatMessageResponse message = chatService.saveMessage(request);
        messagingTemplate.convertAndSend("/topic/chat/" + message.getRentalOrderId(), message);
        messagingTemplate.convertAndSendToUser(
                message.getReceiverId().toString(), "/queue/messages", message);
    }

    @GetMapping("/history/{rentalOrderId}")
    public List<ChatMessageResponse> getHistory(@PathVariable Long rentalOrderId) {
        return chatService.getHistory(rentalOrderId);
    }
}
