package com.example.monitoringservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "messages")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Messages {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    @Column(name = "timestamp", nullable = false, columnDefinition = "BIGINT")
    private Long timestamp;
//    @Column(name = "device_id", nullable = false, columnDefinition = "TEXT")

    @JoinColumn(name = "serialNumber", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Device.class, fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private Device device;

    @Column(name = "serialNumber" )
    private UUID deviceId;

    @Column(name = "measurement_type", nullable = false, columnDefinition = "FLOAT")
    private Float measurement_value;

}
