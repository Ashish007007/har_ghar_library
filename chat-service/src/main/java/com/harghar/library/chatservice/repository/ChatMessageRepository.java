package com.harghar.library.chatservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harghar.library.chatservice.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRentalOrderIdOrderBySentAtAsc(Long rentalOrderId);
}
