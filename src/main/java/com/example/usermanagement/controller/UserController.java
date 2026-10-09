package com.example.usermanagement.controller;
//for standard user

import com.example.usermanagement.dto.ApiResponseDto;
import com.example.usermanagement.dto.CreateUserRequest;

import com.example.usermanagement.dto.UserResponseDTO;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenant/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<UserResponseDTO>> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        User createdUser = userService.createUser(request);

        // Map the database entity to the UserResponseDTO
        UserResponseDTO responseData = new UserResponseDTO();
        responseData.setId(createdUser.getId());
        responseData.setEmail(createdUser.getEmail());
        responseData.setUserType(createdUser.getUserType().name());
        responseData.setActive(createdUser.isActive());

        ApiResponseDto<UserResponseDTO> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(responseData);


        ////  Hand it back with an HTTP 200 OK status
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

