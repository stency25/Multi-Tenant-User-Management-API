package com.example.usermanagement.config;

import com.example.usermanagement.dto.ApiResponseDto;
import com.example.usermanagement.dto.ChangePasswordRequestDto;
import com.example.usermanagement.dto.LoginRequestDto;
import com.example.usermanagement.dto.LoginResponseDto;
import com.example.usermanagement.security.AuthenticatedUser;
import com.example.usermanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenant/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(wrap(authService.login(request)));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> changePassword(
            @Valid @RequestBody ChangePasswordRequestDto request,
            @AuthenticationPrincipal AuthenticatedUser caller) {
        return ResponseEntity.ok(wrap(authService.changePassword(caller, request)));
    }

    private ApiResponseDto<LoginResponseDto> wrap(LoginResponseDto data) {
        ApiResponseDto<LoginResponseDto> response = new ApiResponseDto<>();
        response.setStatus("success");
        response.setData(data);
        return response;
    }
}

