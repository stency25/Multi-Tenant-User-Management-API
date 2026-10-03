package com.example.usermanagement.dto.SuperUserDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateSuperUserRolesRequestDto {
    @NotBlank
    @JsonProperty("organisation_shortcode")
    private String organisationShortcode;

    @NotNull
    @JsonProperty("target_super_user_id")//our specifiec  uder to be updated
    private UUID targetSuperUserId;
@Size(min = 1,message = "at least one role should be assigned")
    @JsonProperty("assigned_role_ids")
    private List<UUID> assignedRoleIds;
}
