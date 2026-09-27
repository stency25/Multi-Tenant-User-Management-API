package com.example.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import java.util.List;

@Data
public class UserRequestDTO {
    @NotBlank
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank
    private String department;

    @NotNull
    @JsonProperty("assigned_role_ids")
    private List<UUID> assignedRoleIds;
}