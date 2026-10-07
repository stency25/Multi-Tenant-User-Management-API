package com.example.usermanagement.service;

/// api/v1/tenant/users
//for stangdard user created by super user
import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.dto.UserRequestDTO;
import com.example.usermanagement.entity.Organisation;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.excemption.DuplicateRequestException;
import com.example.usermanagement.excemption.ResourceNotFoundException;
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
//TJIS METHOD CONFIRMS IF THE USER EXISTS
    public User createUser(CreateUserRequest request) {
        Organisation organisation = organisationRepository
                .findByShortcode(request.getOrganisationShortcode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ORGANISATION_NOT_FOUND", "No organisation exists with this shortcode"));


/// HERE  pull out the nested DTO, check email uniqueness:
    UserRequestDTO userDetails = request.getUserDetails();

    if (userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
        throw new DuplicateRequestException(
                "DUPLICATE_EMAIL", "A user with this email already exists");
    }

    /// BUILD AND SAVE NEW STANdard user





}


