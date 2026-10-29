package com.example.usermanagement.service;


import com.example.usermanagement.dto.ChangePasswordRequestDto;
import com.example.usermanagement.dto.LoginRequestDto;
import com.example.usermanagement.dto.LoginResponseDto;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.excemption.UnauthorizedException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.security.AuthenticatedUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.email())
                .filter(User::isActive)
                .orElseThrow(() -> new UnauthorizedException("INVALID_CREDENTIALS", "Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())){
            throw new UnauthorizedException("INVALID_CREDENTIALS", "Invalid email or password");
        }
        return toResponse(user);

    }

    public LoginResponseDto changePassword(AuthenticatedUser caller, ChangePasswordRequestDto request) {
        User user = userRepository.findById(caller.userId())
                .orElseThrow(() -> new UnauthorizedException("UNAUTHORIZED", "Account no longer exists"));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Current password is incorrect");

        }
        if (passwordEncoder.matches(request.newPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("New password must be different from the current password");
        }
        user.setPasswordHash(passwordEncoder.encode(request.newPassword()));
        user.setRequiresPasswordChange(false);
        userRepository.save(user);
        return toResponse(user);
    }

    private LoginResponseDto toResponse(User user) {
        return new LoginResponseDto(jwtService.generateToken(user), "Bearer",
                jwtService.getExpirationSeconds(), Boolean.TRUE.equals(user.getRequiresPasswordChange()));
    }
}



