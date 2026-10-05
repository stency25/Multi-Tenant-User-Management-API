package com.example.usermanagement.controller;


import com.example.usermanagement.dto.ApiResponseDto;
import com.example.usermanagement.dto.Role.RoleResponseDto;
import com.example.usermanagement.service.RoleService;
import com.sun.net.httpserver.Authenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;


//role endpoint testing controller class
@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {
    private MockMvc mockMvc;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;


    //this method t initializes MockMvc before each test runs.
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }
    //the Test Method Structure
        @Test
     void testGetRolesForTenant_Success()throws Exception{
        ///  adding out the setup variables for building the fake response objects that our mocked service will return
         String tenantShortcode = "acme";
         RoleResponseDto fakeRole =new RoleResponseDto();

         ApiResponseDto<RoleResponseDto>apiResponse= new ApiResponseDto<>();

         apiResponse.setStatus("success");
         apiResponse.setData(fakeRole);

         List<ApiResponseDto<RoleResponseDto>> expectedServiceOutput = Collections.singletonList(apiResponse);

         when(roleService.getRoleForTenant(tenantShortcode)).thenReturn(expectedServiceOutput);
            mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/tenant/roles")
                            .param("organisation_shortcode", tenantShortcode))
                    .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                    .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$[0].status").value("success"));

            org.mockito.Mockito.verify(roleService, org.mockito.Mockito.times(1)).getRoleForTenant(tenantShortcode);
        }
    }





