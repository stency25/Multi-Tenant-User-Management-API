package com.example.usermanagement.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class CreateUserRequest {

    @JsonProperty("organisation_shortcode")
    private String organisationShortcode;

    @JsonProperty("user_details")
    private UserRequestDTO userDetails;
}