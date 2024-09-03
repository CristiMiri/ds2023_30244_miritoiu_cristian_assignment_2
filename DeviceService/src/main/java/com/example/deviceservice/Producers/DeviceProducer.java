package com.example.deviceservice.Producers;

import com.example.deviceservice.entities.Device;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


@Component
public class DeviceProducer {
    private ConnectionFactory factory;
    private static String QUEUE_NAME;

    public DeviceProducer() {
        try {
            setupConnectionFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setupConnectionFactory()  throws Exception{
        factory = new ConnectionFactory();
        factory.setUsername("ffajugyi");
        factory.setPassword("FweNwGiz6zZc3INST2pV7rrkmhWuIheS");
        factory.setVirtualHost("ffajugyi");
        factory.setPort(5672);
        factory.setUri("amqps://ffajugyi:FweNwGiz6zZc3INST2pV7rrkmhWuIheS@sparrow.rmq.cloudamqp.com/ffajugyi");

        QUEUE_NAME = "devices";
    }

    public void send(Device device, String operation)  {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = parseDevice(device, operation);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private static String parseDevice(Device device, String operation) {
        ObjectMapper objectMapper = new ObjectMapper();
        device.setUser(null);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        JsonNode jsonNode = objectMapper.valueToTree(device);
        ((ObjectNode) jsonNode).put("action", operation);
        try {
            return objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
