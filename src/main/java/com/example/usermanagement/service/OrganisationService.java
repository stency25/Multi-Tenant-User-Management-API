package com.example.usermanagement.service;


import com.example.usermanagement.dto.SuperUserDto.AddSuperUserRequestDto;
import com.example.usermanagement.dto.organisationdto.OrganisationRequestDto;
import com.example.usermanagement.entity.Organisation;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.entity.UserType;
import com.example.usermanagement.excemption.DuplicateRequestException;
import com.example.usermanagement.excemption.ResourceNotFoundException;
import com.example.usermanagement.repository.OrganisationRepository;

import com.example.usermanagement.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
public class OrganisationService {

    private static final String TEMP_PASSWORD_ALPHABET=
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz3456789!@#$%";
    private static final int TEMP_PASSWORD_LENGTH=15;


    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom = new SecureRandom();

    public OrganisationService(OrganisationRepository organisationRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.organisationRepository = organisationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Organisation createOrganisation(OrganisationRequestDto request) {

    if (organisationRepository.findByShortcode(request.getOrganisationShortCode()).isPresent()) {
        throw new DuplicateRequestException(
                "DUPLICATE SHORTCODE", "AN ORGANISATION WITH THIS SHORTCODE ALREADY EXISTS");
    }
    Organisation organisation = new Organisation();///this methiod helps create an empty organisation
    organisation.setName(request.getName());
    organisation.setEmail(request.getEmail());
    organisation.setShortcode(request.getOrganisationShortCode());
    organisation.setPhoneNumber(request.getPhoneNumber());
    organisation.setDepartment(request.getDepartment());
    organisation.setLogoUrl(request.getLogoUrl());

    return organisationRepository.save(organisation);//saves to db

}
//for super user
@Transactional
public User addSuperUser(AddSuperUserRequestDto request) {
    Organisation organisation = organisationRepository
            .findByShortcode(request.getOrganisationsShortcode())
            .orElseThrow(() -> new ResourceNotFoundException(
                    "ORGANISATION_NOT_FOUND", "No organisation exists with this shortcode"));

    if (userRepository.findByEmail(request.getSuperUserDetails().getEmail()).isPresent()) {
        throw new DuplicateRequestException(
                "DUPLICATE_EMAIL", "A user with this email already exists");
    }

    String temporaryPassword = generateTemporaryPassword();

    User superUser = new User();
    superUser.setOrganisation(organisation);
    superUser.setEmail(request.getSuperUserDetails().getEmail());
    superUser.setFullName(request.getSuperUserDetails().getFullName());
    superUser.setDepartment(request.getSuperUserDetails().getDepartment());
    superUser.setPhoneNumber(request.getSuperUserDetails().getPhoneNumber());
    superUser.setUserType(UserType.SUPER_USER);
    superUser.setPasswordHash(passwordEncoder.encode(temporaryPassword));
    superUser.setActive(true);
    superUser.setRequiresPasswordChange(true);

    return userRepository.save(superUser);
}
///This method builds a random,unnpredictable temporary password
private String generateTemporaryPassword(){
        StringBuilder  sb =new StringBuilder(TEMP_PASSWORD_LENGTH);
        for (int i = 0; i < TEMP_PASSWORD_LENGTH; i++ ){
        sb.append(TEMP_PASSWORD_ALPHABET.charAt(secureRandom.nextInt(TEMP_PASSWORD_ALPHABET.length())));
    }
        return sb.toString();
}


}
