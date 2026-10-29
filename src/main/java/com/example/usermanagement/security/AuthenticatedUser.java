package com.example.usermanagement.security;

import com.example.usermanagement.entity.UserType;

import java.util.UUID;
//Represents the currently authenticated user.
 // This immutable record is typically stored in the security context after a JWT is validated.

public record AuthenticatedUser(UUID userId, String email, UserType userType,String organisationShortCode,Boolean requiresPasswordChange) {
}
