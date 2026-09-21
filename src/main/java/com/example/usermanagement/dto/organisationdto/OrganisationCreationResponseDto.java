package com.example.usermanagement.dto.organisationdto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class OrganisationCreationResponseDto {
    @JsonProperty("organisation_id")
    private UUID organisationId;
    @JsonProperty("organisation_shortcode")
    private String  organisationShortcode;
    @JsonProperty("super_user_id")
    private String superUserId;
    private String email;
}
