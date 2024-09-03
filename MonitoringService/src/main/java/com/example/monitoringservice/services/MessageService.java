package com.example.monitoringservice.services;

import com.example.monitoringservice.entities.Device;
import com.example.monitoringservice.entities.Messages;
import com.example.monitoringservice.repositories.MessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final DeviceService deviceService;
    private final WebSocketService webSocketService;


    @Autowired
    public MessageService(MessageRepository messageRepository, DeviceService deviceService, WebSocketService webSocketService) {
        this.messageRepository = messageRepository;
        this.deviceService = deviceService;
        this.webSocketService = webSocketService;
    }

    public Messages saveMessage(Messages message) {
        return messageRepository.save(message);
    }

    public List<Messages> findAll() {
        return messageRepository.findAll();
    }

    public List<Messages> findAllByDeviceId(UUID deviceId) {
       return messageRepository.findAllByDeviceId(deviceId);}


    public void deleteAllbyDeviceId(UUID deviceId) {
        messageRepository.deleteAllByDeviceId(deviceId);
    }


    public void parseMessage(String message) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);

            Messages messageToSave = Messages.builder()
                    .timestamp(jsonNode.get("timestamp").asLong())
                    .measurement_value(jsonNode.get("measurement_value").floatValue())
                    .deviceId(UUID.fromString(jsonNode.get("device_id").asText()))
                    .build();
//            check if device exists in database
            if (deviceService.findBySerialNumber(messageToSave.getDeviceId()) == null) {
                System.out.println("Device with serial number " + messageToSave.getDeviceId() + " does not exist");
                return;
            }
            messageRepository.save(messageToSave);
            Device device = deviceService.findBySerialNumber(messageToSave.getDeviceId());
            int dangerlevel = 2;
            boolean stop = messageToSave.getMeasurement_value() <= device.getMaxConsumption()* dangerlevel;
            if (messageToSave.getMeasurement_value() > device.getMaxConsumption() && stop) {
                this.sendNotification(device);
            }

        } catch (Exception e) {
            System.out.println("Error parsing message: " + e.getMessage());
        }
    }
    private void sendNotification(Device device) {
        webSocketService.sendMessageToUser(
                String.valueOf(device.getUserId()),
                "Device " + device.getName() + " has exceeded its maximum consumption!");
        System.out.println("Device " + device.getName() + " has exceeded its maximum consumption!");
        System.out.println("Sending message to user " + device.getUserId());
    }
    public List<Messages> findAllByUserId(Long userID) {
        List<Device> devices = deviceService.findAllByUserId(userID);
        if (devices.isEmpty()) {
            new java.util.ArrayList<Messages>();
        }
        List<Messages> messages = new java.util.ArrayList<>();
        for (Device device : devices) {
            messages.addAll(messageRepository.findAllByDeviceId(device.getSerialNumber()));
        }
        return messages;
    }
}
