package com.example.usermanagement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class CreateUserRequest {
    @NotBlank
    @JsonProperty("organisation_shortcode")
    private String organisationShortcode;

    @NotNull
    @Valid
    @JsonProperty("user_details")
    private UserRequestDTO userDetails;
}