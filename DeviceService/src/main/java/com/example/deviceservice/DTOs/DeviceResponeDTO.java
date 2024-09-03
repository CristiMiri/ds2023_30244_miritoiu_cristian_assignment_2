package com.example.deviceservice.DTOs;

import com.example.deviceservice.entities.DeviceStatus;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponeDTO {
    private String serialNumber;
    private String name;
    private String description;
    private DeviceStatus status;
    private String address;
    private Float maxConsumption;
    private Long userId;
}
