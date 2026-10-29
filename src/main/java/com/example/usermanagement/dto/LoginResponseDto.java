package com.example.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponseDto(
        @JsonProperty("access_token")String accessToken,
        @JsonProperty("token_type")String  tokenType,
        @JsonProperty("expires_in")Long expiresIn,
        @JsonProperty("require_password_change") Boolean requiresPasswordChange) {
}
