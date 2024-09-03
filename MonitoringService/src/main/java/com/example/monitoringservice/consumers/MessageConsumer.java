package com.example.monitoringservice.consumers;

import com.example.monitoringservice.controllers.WebSocketController;
import com.example.monitoringservice.services.MessageService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {


    private final MessageService messageService;


    @Autowired
    public MessageConsumer(MessageService messageService) {
        this.messageService = messageService;
    }

    @RabbitListener(queues = "readings")
    public void handleMessage(String message) {
        System.out.println("Received message: " + message);
        messageService.parseMessage(message);
    }

}
