package com.example.usermanagement.controller;


import com.example.usermanagement.dto.Role.ApiResponseDto;
import com.example.usermanagement.dto.Role.RoleResponseDto;
import com.example.usermanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenant")
public class RoleController {

    private  final RoleService roleService;
@Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<ApiResponseDto<List<RoleResponseDto>>>getRolesForTenant(
            @RequestParam("organisation_shortcode") String organisationShortcode){
    List<RoleResponseDto> roles =roleService.getRoleForTenant(organisationShortcode);//fetch data from servce
        ApiResponseDto<List<RoleResponseDto>> response = new ApiResponseDto<>("success", roles);//wrap  result s to api res ponse

        return ResponseEntity.ok(response);

    }


}
