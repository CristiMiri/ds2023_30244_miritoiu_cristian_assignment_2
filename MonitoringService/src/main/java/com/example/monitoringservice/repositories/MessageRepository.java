package com.example.monitoringservice.repositories;

import com.example.monitoringservice.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Messages, UUID> {
    List<Messages> findAllByDeviceId(UUID deviceId);

    void deleteAllByDeviceId(UUID deviceId);
}
