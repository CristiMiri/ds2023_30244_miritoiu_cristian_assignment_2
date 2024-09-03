package org.example;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;



public class Sender {

    private ConnectionFactory factory;
    private static String QUEUE_NAME;

    private void setupConnectionFactory() throws Exception {
        factory = new ConnectionFactory();
        String configFilePath = "src/main/java/org/example/config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties prop = new Properties();
        prop.load(propsInput);
        factory.setUsername(prop.getProperty("RABBITMQ_HOST"));
        factory.setPassword(prop.getProperty("RABBITMQ_PASSWORD"));
        factory.setVirtualHost(prop.getProperty("RABBITMQ_VIRTUAL_HOST"));
        factory.setPort(Integer.parseInt(prop.getProperty("RABBITMQ_PORT")));
        factory.setUri(prop.getProperty("RABBITMQ_URL"));
        QUEUE_NAME = prop.getProperty("RABBITMQ_QUEUE");
    }
    public Sender() {
        try {
            setupConnectionFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  void send(Message message) throws Exception {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //add new property to json object and convert it to string
            // action is the new property with value "update"

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            JsonNode jsonNode = objectMapper.valueToTree(message);
//            ((ObjectNode) jsonNode).put("action", "update");
            String messageToSend = objectMapper.writeValueAsString(jsonNode);
            channel.basicPublish("", QUEUE_NAME, null, messageToSend.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + messageToSend + "'");
        }
    }
}
