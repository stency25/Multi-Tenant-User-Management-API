package com.example.usermanagement.service;

/// api/v1/tenant/users
//for stangdard user created by super user
import com.example.usermanagement.repository.OrganisationRepository;
import com.example.usermanagement.repository.RoleRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.repository.UserRoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {

    private static final String TEMP_PASSWORD_ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz3456789!@#$%&";
    private static final int TEMP_PASSWORD_LENGTH=15;


    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecureRandom secureRandom= new SecureRandom();

    public UserService(OrganisationRepository organisationRepository, UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.organisationRepository = organisationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }





}


