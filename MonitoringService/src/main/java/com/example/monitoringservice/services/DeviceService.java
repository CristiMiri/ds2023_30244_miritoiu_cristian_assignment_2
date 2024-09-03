package com.example.monitoringservice.services;

import com.example.monitoringservice.entities.Device;
import com.example.monitoringservice.repositories.DeviceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {

        private final DeviceRepository deviceRepository;

        @Autowired
        public DeviceService(DeviceRepository deviceRepository) {
            this.deviceRepository = deviceRepository;
        }

        public Device saveDevice(Device device) {
            return deviceRepository.save(device);
        }

        public List<Device> findAll() {
            return deviceRepository.findAll();
        }

        public Device findBySerialNumber(UUID serialNumber) {
            return deviceRepository.findBySerialNumber(serialNumber);
        }

        public Device updateDevice(Device device) {
            Device deviceToUpdate = deviceRepository.findBySerialNumber(device.getSerialNumber());
            deviceToUpdate.setMaxConsumption(device.getMaxConsumption());
            deviceToUpdate.setUserId(device.getUserId());
            return deviceRepository.save(deviceToUpdate);
//            return deviceRepository.save(device);
        }

        public Device deleteBySerialNumber(UUID serialNumber) {
            try {
                Device deviceToDelete = deviceRepository.findBySerialNumber(serialNumber);
                deviceRepository.delete(deviceToDelete);

            } catch (Exception e) {
                System.out.println("Device with serial number " + serialNumber + " does not exist");
                return null;
            }
            System.out.println("Device with serial number " + serialNumber + " deleted");
            return null;
        }

        public List<Device> findAllByUserId(Long userId) {
            return deviceRepository.findAllByUserId(userId);
        }

        public void deleteAllByUserId(Long userId) {
            deviceRepository.deleteAllByUserId(userId);
        }

        public void deleteAll() {
            deviceRepository.deleteAll();
        }



}
