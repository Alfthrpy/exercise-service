package com.example.exercise_service.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercise_service.service.KafkaConsumerService;
import com.example.exercise_service.service.KafkaProducerService;

import org.springframework.web.multipart.MultipartFile;


@RestController
public class KafkaController {

    private final KafkaProducerService producerService;
    private final KafkaConsumerService consumerService;

    public KafkaController(KafkaProducerService producerService, KafkaConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }



    @PostMapping("/send")
    public String sendMessageBatch(@RequestParam("file") MultipartFile file) {
    
        try (BufferedReader reader =
                 new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                
            String line;
                
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    producerService.sendMessage(line);
                }
            }
        
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    
        return "Message sent successfully!";
    }

    @GetMapping("/consume")
    public org.springframework.http.ResponseEntity<?> getConsumedMessages() {
        java.util.List<String> messages = consumerService.getMessages();
        return org.springframework.http.ResponseEntity.ok(
            java.util.Map.of(
                "batchSize", messages.size(),
                "messages", messages
            )
        );
    }



}