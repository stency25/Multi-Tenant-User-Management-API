package com.example.usermanagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrganisatioRequestDto {
    private String name;

    @JsonProperty("organisation_shortcode")
    private String organisationShortCode;

    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("phone_number")
    private String phone_number;

    private String email;

    private String department;
}
