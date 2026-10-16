package com.example.usermanagement.service;

/// api/v1/tenant/users
//for stangdard user created by super user
import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.dto.UpdateUserRolesRequestDto;
import com.example.usermanagement.dto.UserRequestDTO;
import com.example.usermanagement.entity.*;
import com.example.usermanagement.excemption.DuplicateRequestException;
import com.example.usermanagement.excemption.PermissionDeniedException;
import com.example.usermanagement.excemption.ResourceNotFoundException;
import com.example.usermanagement.repository.OrganisationRepository;
import com.example.usermanagement.repository.RoleRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.repository.UserRoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.UUID;

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

        String temporaryPassword = generateTemporaryPassword();

        User standardUser = new User();
        standardUser.setOrganisation(organisation);
        standardUser.setEmail(userDetails.getEmail());
        standardUser.setFullName(userDetails.getFullName());
        standardUser.setPhoneNumber(userDetails.getPhoneNumber());
        standardUser.setDepartment(userDetails.getDepartment());
        standardUser.setUserType(UserType.STANDARD_USER);
        standardUser.setPasswordHash(bCryptPasswordEncoder.encode(temporaryPassword));
        standardUser.setActive(true);
        standardUser.setRequiresPasswordChange(true);

        User savedUser = userRepository.save(standardUser);

    return savedUser;
}

    private String generateTemporaryPassword() {
        StringBuilder sb = new StringBuilder(TEMP_PASSWORD_LENGTH);
        for (int i = 0; i < TEMP_PASSWORD_LENGTH; i++) {
            sb.append(TEMP_PASSWORD_ALPHABET.charAt(secureRandom.nextInt(TEMP_PASSWORD_ALPHABET.length())));
        }
        return sb.toString();
    }



///
    @Transactional
    public void updateUserRoles(UpdateUserRolesRequestDto request) {

        Organisation organisation = organisationRepository
                .findByShortcode(request.getOrganisationShortcode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ORGANISATION_NOT_FOUND", "No organisation exists with this shortcode"));

        User targetUser = userRepository
                .findById(request.getTargetUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "USER_NOT_FOUND", "No user exists with this ID"));

        if (targetUser.getOrganisation() == null
                || !targetUser.getOrganisation().getId().equals(organisation.getId())) {
            throw new ResourceNotFoundException(
                    "USER_NOT_FOUND", "No user exists with this ID");
        }

        if (targetUser.getUserType() != UserType.STANDARD_USER) {
            throw new PermissionDeniedException(
                    "PERMISSION_DENIED",
                    "Super users cannot modify roles or permissions for other super users or themselves. Contact System Administrator.");
        }

        userRoleRepository.deleteByUser_Id(targetUser.getId());

        for (UUID roleId : request.getAssignedRoleIds()) {
            RoleEntity role = roleRepository
                    .findById(roleId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "ROLE_NOT_FOUND", "No role exists with ID: " + roleId));

            UserRolesEntity userRole = new UserRolesEntity();
            userRole.setUser(targetUser);
            userRole.setRole(role);
            userRole.setOrganisation(organisation);

            userRoleRepository.save(userRole);
        }
    }


}


