package com.example.usermanagement.dto.Role;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {
    private UUID RoleId;
    private String name;
    private String  description;

}
