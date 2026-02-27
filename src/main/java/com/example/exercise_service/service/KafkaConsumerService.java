package com.example.exercise_service.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    // in-memory list to keep received messages
    private final java.util.List<String> messages = new java.util.concurrent.CopyOnWriteArrayList<>();

    @KafkaListener(topics = "stress-test", groupId = "group_id")
    public void consume(List<String> messages) {
        this.messages.addAll(messages);
    }

    /**
     * Returns unmodifiable list of all consumed messages.
     */
    public java.util.List<String> getMessages() {
        return java.util.Collections.unmodifiableList(messages);
    }
}