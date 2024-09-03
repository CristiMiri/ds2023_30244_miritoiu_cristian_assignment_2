package com.example.deviceservice.services;

import com.example.deviceservice.DTOs.DeviceRequestDTO;
import com.example.deviceservice.DTOs.DeviceResponeDTO;
import com.example.deviceservice.Producers.DeviceProducer;
import com.example.deviceservice.entities.Device;
import com.example.deviceservice.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceProducer deviceProducer;
    @Autowired
    public DeviceService(DeviceRepository deviceRepository, DeviceProducer deviceProducer) {
        this.deviceRepository = deviceRepository;
        this.deviceProducer = deviceProducer;
    }

    public List<DeviceResponeDTO> findDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream()
                .map(this::mapToDeviceResponseDTO)
                .collect(java.util.stream.Collectors.toList());
    }




    public DeviceResponeDTO findDevicebySerialNumber(UUID serialNumber) {
        Device device = deviceRepository.findBySerialNumber(serialNumber);
        return mapToDeviceResponseDTO(device);
    }

    public DeviceResponeDTO findDeviceBySerialNumber(String serialNumber) {
        Device device = deviceRepository.findBySerialNumber(UUID.fromString(serialNumber));
        return mapToDeviceResponseDTO(device);
    }


    public DeviceResponeDTO insert(DeviceRequestDTO deviceRequestDTO) {
        Device device = mapToDevice(deviceRequestDTO);
        device = deviceRepository.save(device);
        String operation = "insert";

        deviceProducer.send(device, operation);
        return mapToDeviceResponseDTO(device);
    }



    public DeviceResponeDTO update(DeviceRequestDTO deviceRequestDTO) {
        Device device = mapToDevice(deviceRequestDTO);
        Device beforeUpdate = deviceRepository.findBySerialNumber(UUID.fromString(deviceRequestDTO.getSerialNumber()));
        device.setSerialNumber(beforeUpdate.getSerialNumber());
        device = deviceRepository.save(device);
        String operation = "update";
        deviceProducer.send(device, operation);
        return mapToDeviceResponseDTO(device);
    }

    public void delete(UUID deviceId) {
        Device device = deviceRepository.findBySerialNumber(deviceId);
        deviceRepository.delete(device);
        String operation = "delete";
        deviceProducer.send(device, operation);
    }


    public void mapDevice(DeviceRequestDTO deviceRequestDTO) {
        Device device = mapToDevice(deviceRequestDTO);
        device = deviceRepository.save(device);
    }

    public List<DeviceResponeDTO> findDevicesByUserId(Long userId) {
        List<Device> devices = deviceRepository.findByUserId(userId);
        return devices.stream()
                .map(this::mapToDeviceResponseDTO)
                .collect(java.util.stream.Collectors.toList());
    }


    private DeviceResponeDTO mapToDeviceResponseDTO(Device device) {
        return DeviceResponeDTO.builder()
                .serialNumber(String.valueOf(device.getSerialNumber()))
                .name(device.getName())
                .description(device.getDescription())
                .status(device.getStatus())
                .address(device.getAddress())
                .maxConsumption(device.getMaxConsumption())
                .userId(device.getUserId())
                .build();
    }

    private Device mapToDevice(DeviceRequestDTO deviceRequestDTO) {
        return Device.builder()
//                .serialNumber(UUID.fromString(deviceRequestDTO.getSerialNumber()))
                .description(deviceRequestDTO.getDescription())
                .name(deviceRequestDTO.getName())
                .status(deviceRequestDTO.getStatus())
                .address(deviceRequestDTO.getAddress())
                .maxConsumption(deviceRequestDTO.getMaxConsumption())
                .userId(deviceRequestDTO.getUserId())
                .build();
    }

    public void deleteByUserId(Long userId) {
        List<Device> devices = deviceRepository.findByUserId(userId);
        System.out.println(devices.toString());
        deviceRepository.deleteAll(devices);
    }
}
