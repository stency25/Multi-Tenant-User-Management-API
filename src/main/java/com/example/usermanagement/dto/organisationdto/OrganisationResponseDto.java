package com.example.usermanagement.dto.organisationdto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class OrganisationResponseDto {

    private UUID id;
    private String name;

    @JsonProperty("organisation_shortcode")
    private String organisationShortCode;

    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;
    private String department;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
}
