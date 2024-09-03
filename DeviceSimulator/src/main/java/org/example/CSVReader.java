package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import static java.lang.Thread.sleep;

public class CSVReader {
    private final String csvFile="src/main/resources/sensor.csv";
    private final int lineCount=1000;

    private String device_id;
    public  void readCSV(String device_id) {
        this.device_id = device_id;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            Long onehour = 3600000L;
            final Long incement = 3600000L;
            // Read the file line by line
            for (int i = 0; i < lineCount; i++) {
                line = br.readLine();
                sleep(1000);
                Message message = createMessage(line, device_id, onehour);
                Sender sender = new Sender();
                sender.send(message);
                onehour += incement;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Message createMessage(String line, String device_id,long onehour){
        String[] values = line.split(",");
        Long timestamp = System.currentTimeMillis();

        timestamp = timestamp + onehour;

        Float measurement_value = Float.parseFloat(values[0]);
        measurement_value = randomfactor(measurement_value);
        return new Message(timestamp, device_id, measurement_value);
    }
    private static float randomfactor(float value){
        Random random = new Random();
        float randomValue = random.nextFloat() * (50 - 0) + 0;
        value = value - randomValue;
        if (value < 0) {
            value = -value;
        }
        return value;
    }
}
