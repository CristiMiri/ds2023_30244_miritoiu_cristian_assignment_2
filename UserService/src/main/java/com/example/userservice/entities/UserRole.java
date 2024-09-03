package com.example.userservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRole {
    @JsonProperty("admin")
    ADMIN,
    @JsonProperty("user")
    USER
}
