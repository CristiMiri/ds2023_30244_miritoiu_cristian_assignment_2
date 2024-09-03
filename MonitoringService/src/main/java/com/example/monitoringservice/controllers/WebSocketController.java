package com.example.monitoringservice.controllers;

import com.example.monitoringservice.entities.Messages;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
//@CrossOrigin(origins = "*", allowedHeaders = "*")

public class WebSocketController {
    // Handle messages sent to "/app/hello" destination
    final SimpMessagingTemplate template;
    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings") // Broadcast the response to "/topic/greetings"
    public String greeting(String message) throws Exception {
        // Simulate some processing
        Thread.sleep(1000);
        System.out.println("Message received: " + message);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(message);
        System.out.println("JSON: " + json.get("name"));
//         Create a greeting message and send it to subscribers
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message) + "!");
        String response = "Hello, " + json.get("name") + "!";
        return response;
    }
//    @SendTo("/topic/greetings/{id}")
//    public String notify(Messages message) throws Exception {
//            // Simulate some processing
//            Thread.sleep(10000);
//
//            template.convertAndSend("/topic/greetings/{id}", message);
//            System.out.println("Message sent: " + message);
//            return message;
//    }
}
