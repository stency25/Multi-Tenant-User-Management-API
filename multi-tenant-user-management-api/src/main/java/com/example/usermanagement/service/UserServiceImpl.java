package com.example.usermanagement.service;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.dto.UserRequestDTO;
import com.example.usermanagement.dto.UserResponseDTO;
import com.example.usermanagement.entity.Organisation;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.entity.UserType;
import com.example.usermanagement.repository.OrganisationRepository;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public UserResponseDTO createUser(CreateUserRequest request) {

        Organisation organisation = organisationRepository
                .findByShortcode(request.getOrganisationShortcode())
                .orElseThrow(() -> new RuntimeException("Organisation not found"));

        UserRequestDTO details = request.getUserDetails();

        User user = new User();
        user.setOrganisation(organisation);
        user.setFullName(details.getFullName());
        user.setEmail(details.getEmail());
        user.setPhoneNumber(details.getPhoneNumber());
        user.setDepartment(details.getDepartment());
        user.setUserType(UserType.STANDARD_USER);
        user.setActive(true);

        // Temporary placeholder — real apps must hash with BCrypt (see note below)
        user.setPasswordHash(UUID.randomUUID().toString());

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getEmail(),
                saved.getUserType().name(),
                saved.isActive()
        );
    }
}