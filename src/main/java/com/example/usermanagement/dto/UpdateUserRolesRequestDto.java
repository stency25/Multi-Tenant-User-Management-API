package com.example.usermanagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateUserRolesRequestDto {
    @NotBlank
    @JsonProperty("organisation_shortcode")
    private String organisationShortcode;

    @NotNull
    @JsonProperty("target_user_id")
    private UUID targetUserId;

    @NotNull
    @JsonProperty("assigned_role_ids")
    private List<UUID> assignedRoleIds;

}
