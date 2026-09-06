package com.example.usermanagement.service;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(CreateUserRequest request);
}