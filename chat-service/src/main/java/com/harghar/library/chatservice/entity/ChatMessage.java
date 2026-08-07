package com.harghar.library.chatservice.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long rentalOrderId;

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID senderId;

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID receiverId;

    @Column(nullable = false, columnDefinition = "text")
    private String messageContent;

    @Column(nullable = false, updatable = false)
    private Instant sentAt;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;
}
