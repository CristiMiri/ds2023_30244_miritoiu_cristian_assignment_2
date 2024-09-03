package com.example.monitoringservice.config;

import com.example.monitoringservice.controllers.WebSocketController;
import com.example.monitoringservice.entities.Device;
import com.example.monitoringservice.entities.Messages;
import com.example.monitoringservice.repositories.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

import static java.lang.Thread.sleep;

@Configuration
public class DatabaseConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(MessageRepository messageRepository, WebSocketController webSocketController) {
        if (messageRepository.count() != 0) {
            return args -> {
//                System.out.println("Sending message...");
//                webSocketController.notify("Hello, world!");
            };
        }
        return args -> {
            Messages defaultMessage1 = Messages.builder()
                    //cast timestamp to timestamp for databse
                    .timestamp(123456789L)
                    .measurement_value(100f)
                    .deviceId(UUID.fromString("95c683df-5040-4590-a14c-f89bfe52e33e"))
                    .build();
            Messages defaultMessage2 = Messages.builder()
                    .timestamp(123456789L)
                    .measurement_value(200f)
                    .deviceId(UUID.fromString("95c683df-5040-4590-a14c-f89bfe52e33e"))
                    .build();
            Device defaultDevice = Device.builder()
                    .serialNumber(UUID.fromString("95c683df-5040-4590-a14c-f89bfe52e33e"))
                    .maxConsumption(100f)
                    .userId(1L)
                    .build();
            System.out.println(defaultMessage1.toString());

//            deviceRepository.save(defaultDevice);
//            messageRepository.saveAll(java.util.List.of(defaultMessage1, defaultMessage2));
        };
    }
}
