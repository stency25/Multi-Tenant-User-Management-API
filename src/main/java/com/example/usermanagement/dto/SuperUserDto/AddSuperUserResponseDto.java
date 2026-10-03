package com.example.usermanagement.dto.SuperUserDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AddSuperUserResponseDto {

    @JsonProperty("organisation_shortcode")
    private String organisationShortcode;

    @JsonProperty("super_user_id")
    private UUID superUserId;

    private String email;
}

