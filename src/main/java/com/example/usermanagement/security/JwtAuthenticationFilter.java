package com.example.usermanagement.security;

import com.example.usermanagement.entity.UserType;
import com.example.usermanagement.service.JwtService;
import com.sun.net.httpserver.Filter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.UUID;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header  != null &&header.startsWith("Bearer") ){
            try {
                Claims claims = jwtService.parse(header.substring(7));

                AuthenticatedUser Principal = new AuthenticatedUser(
                        UUID.fromString(claims.getSubject()),
                        claims.get("email", String.class),
                        UserType.valueOf(claims.get("user_type", String.class)),
                        claims.get("org", String.class),
                        Boolean.TRUE.equals(claims.get("requires_password_change", Boolean.class)));


                GrantedAuthority authority = Principal.requiresPasswordChange()
                        ? new SimpleGrantedAuthority("PASSWORD_CHANGE_REQUIRED")
                        : new SimpleGrantedAuthority("ROLE_" + Principal.userType().name());

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(Principal, null, List.of(authority)));

            }
            catch (JwtException | IllegalArgumentException exception){
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request,response);




    }
}
