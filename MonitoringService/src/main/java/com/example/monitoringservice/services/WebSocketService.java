package com.example.monitoringservice.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
//
    public void sendMessageToTopic(String message) {
        messagingTemplate.convertAndSend("/topic/messages", "Broadcast message: " + message);
    }
//
    public void sendMessageToUser(String username, String message) {
        messagingTemplate.convertAndSendToUser(username, "/queue/messages", message);
        //url = /user/{username}/queue/messages
        System.out.println("Message sent to user: " + username);
    }
}
