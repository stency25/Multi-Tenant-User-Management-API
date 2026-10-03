package com.example.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    @JsonProperty("user_id")
    private UUID id;

    private String email;

    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("is_active")
    private boolean isActive;
}