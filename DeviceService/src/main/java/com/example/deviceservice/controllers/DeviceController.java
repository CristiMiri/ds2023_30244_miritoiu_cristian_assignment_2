package com.example.deviceservice.controllers;

import com.example.deviceservice.DTOs.DeviceRequestDTO;
import com.example.deviceservice.DTOs.DeviceResponeDTO;
import com.example.deviceservice.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/devices")
public class DeviceController {
    private final DeviceService deviceService;
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

        @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DeviceResponeDTO>> getDevices() {
        List<DeviceResponeDTO> dtos = deviceService.findDevices();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeviceResponeDTO> getDevice(@PathVariable("id") UUID deviceId) {
        DeviceResponeDTO dto = deviceService.findDevicebySerialNumber(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<DeviceResponeDTO> getDevicebySerialNumber(@RequestBody DeviceRequestDTO deviceRequestDTO)     {
        DeviceResponeDTO dto = deviceService.findDeviceBySerialNumber(deviceRequestDTO.getSerialNumber());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/insert" , consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeviceResponeDTO> insertDevice(@RequestBody DeviceRequestDTO deviceRequestDTO) {
        System.out.println(deviceRequestDTO);
        DeviceResponeDTO deviceResponeDTO = deviceService.insert(deviceRequestDTO);
        return new ResponseEntity<>(deviceResponeDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update" , consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeviceResponeDTO> updateDevice(@RequestBody DeviceRequestDTO deviceRequestDTO) {
        DeviceResponeDTO deviceResponeDTO = deviceService.update(deviceRequestDTO);
        return new ResponseEntity<>(deviceResponeDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteDevice(@PathVariable("id") UUID deviceId) {
        deviceService.delete(deviceId);
        return new ResponseEntity<>("Device deleted successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/mapdevice")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> mapDevice(@RequestBody DeviceRequestDTO deviceRequestDTO) {
        deviceService.mapDevice(deviceRequestDTO);
        return new ResponseEntity<>("Device mapped successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/getdevicesbyuserid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DeviceResponeDTO>> getDevicesByUserId(@PathVariable("id") Long userId) {
        List<DeviceResponeDTO> dtos = deviceService.findDevicesByUserId(userId);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}
