package com.example.usermanagement.dto.SuperUserDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


//to check on this class
@Data
public class AddSuperUserRequestDto {
    @NotBlank
    @JsonProperty("organisation_shortcode")
    private String organisationsShortcode;

    @NotNull
    @Valid
    @JsonProperty("super_user_details")
    private SuperUserDetailsDto superUserDetails;
}
