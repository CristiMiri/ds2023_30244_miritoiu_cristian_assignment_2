package com.example.monitoringservice.consumers;

import com.example.monitoringservice.entities.Device;
import com.example.monitoringservice.services.DeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeviceConsumer {

    private final DeviceService deviceService;

    @Autowired
    public DeviceConsumer(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    @RabbitListener(queues = "devices")
    public void handleMessage(String message) {
        System.out.println("Received message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String action = jsonNode.get("action").asText();
            Device device = parseDevice(jsonNode);
            switch (action) {
                case "insert":
                    deviceService.saveDevice(device);
                    break;
                case "update":
                    deviceService.updateDevice(device);
                    break;
                case "delete":
                    deviceService.deleteBySerialNumber(device.getSerialNumber());
                    break;
                default:
                    throw new RuntimeException("Unknown action: " + action);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }





    }
    private static Device parseDevice(JsonNode jsonNode) {
            Device device= Device.builder()
                    .serialNumber(UUID.fromString(jsonNode.get("serialNumber").asText()))
                    .maxConsumption(jsonNode.get("maxConsumption").floatValue())
                    .userId(jsonNode.get("userId").asLong())
                    .name(jsonNode.get("name").asText())
                    .build();
            if (jsonNode.get("userId").asText().equals("null")) {
                device.setUserId(null);
            }
            System.out.println("Device parsed: " + device);
            return device;
    }


}
