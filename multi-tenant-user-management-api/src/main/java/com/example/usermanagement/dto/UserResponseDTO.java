package com.example.usermanagement.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String email;
    private String userType;
    private boolean isActive;
}