package com.example.monitoringservice.repositories;

import com.example.monitoringservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    Device findBySerialNumber(UUID serialNumber);
    List<Device> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
