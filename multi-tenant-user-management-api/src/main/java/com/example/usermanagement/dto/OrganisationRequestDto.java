package com.example.usermanagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganisationRequestDto {
    @NotBlank
    private String name;

    @JsonProperty("organisation_shortcode")
    private String organisationShortCode;

    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @Email
    private String email;

    private String department;
}
