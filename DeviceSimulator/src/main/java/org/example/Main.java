package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            String deviceName  = args[0];
            System.out.println("Device name: " + deviceName);
            CSVReader csvReader = new CSVReader();
            csvReader.readCSV(deviceName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}