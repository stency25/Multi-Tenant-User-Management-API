package com.example.usermanagement.security;

import com.example.usermanagement.entity.UserType;

import java.util.UUID;

public record AuthenticatedUser(UUID UserId, String email, UserType userType,String organisationShortCode,Boolean requiresPasswordChange) {
}
