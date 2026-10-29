package com.example.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequestDto(
        @NotBlank @JsonProperty("current_password")String currentPassword,
        @NotBlank @Size (min = 8,message = "must be at least 8 characters")
        @JsonProperty("new_password")String newPassword) {
}
