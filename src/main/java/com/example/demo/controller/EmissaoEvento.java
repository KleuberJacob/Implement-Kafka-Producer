package com.example.demo.controller;

import com.example.demo.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtor-app")
public class EmissaoEvento {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publicar/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for (int i = 0; i <= 10000; i++) {
                publisher.sendMessageToTopic(message + ": " + i);
            }
            return ResponseEntity.ok("Mensagem publicada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
