package com.example.usermanagement.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import java.util.List;

@Data
public class UserRequestDTO {

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String department;

    @JsonProperty("assigned_role_ids")
    private List<UUID> assignedRoleIds;
}