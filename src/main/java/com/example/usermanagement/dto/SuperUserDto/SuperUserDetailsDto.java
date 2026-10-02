package com.example.usermanagement.dto.SuperUserDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SuperUserDetailsDto {
    @NotBlank
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank
    private String department;


}
