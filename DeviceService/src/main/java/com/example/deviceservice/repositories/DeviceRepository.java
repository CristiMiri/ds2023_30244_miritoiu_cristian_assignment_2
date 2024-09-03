package com.example.deviceservice.repositories;

import com.example.deviceservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Device findBySerialNumber(UUID serialNumber);

    List<Device> findByUserId(java.lang.Long userId);
}
