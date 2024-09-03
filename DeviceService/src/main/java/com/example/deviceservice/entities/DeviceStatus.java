package com.example.deviceservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeviceStatus {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("inactive")
    INACTIVE,
    @JsonProperty("unknown")
    UNKNOWN

}
