package org.example;

public class Message implements java.io.Serializable {

    private Long timestamp;
    private String device_id;
    private Float measurement_value;

    public Message(Long timestamp, String device_id, Float measurement_value) {
        this.timestamp = timestamp;
        this.device_id = device_id;
        this.measurement_value = measurement_value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", device_id='" + device_id + '\'' +
                ", measurement_value=" + measurement_value +
                '}';
    }
}
