package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("javatechie-demo3", message);
        future.whenComplete((result, exception) -> {
           if(exception == null) {
               System.out.println("Mensagem enviada=[" + message + "] no offset=[" + result.getRecordMetadata().offset() + "]");
           } else {
               System.out.println("Erro ao enviar mensagem=[" + message + "] : " + exception.getMessage());
           }
        });
    }

}
