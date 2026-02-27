package com.example.exercise_service.controller;

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
        try {
            String content = new String(file.getBytes());
            String[] messages = content.split("\\r?\\n");
            for (String message : messages) {
                producerService.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in sending messages: " + e.getMessage();
        }
        return "Message sent successfully!";
    }

    @GetMapping("/consume")
    public java.util.List<String> getConsumedMessages() {
        return consumerService.getMessages();
    }



}