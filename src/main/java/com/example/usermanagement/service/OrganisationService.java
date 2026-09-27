package com.example.usermanagement.service;


import com.example.usermanagement.config.PasswordEncoderConfig;
import com.example.usermanagement.dto.UserRequestDTO;
import com.example.usermanagement.dto.organisationdto.OrganisationRequestDto;
import com.example.usermanagement.entity.Organisation;
import com.example.usermanagement.excemption.DuplicateRequestException;
import com.example.usermanagement.repository.OrganisationRepository;

import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.UUID;

@Service
public class OrganisationService {

    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoderConfig;
    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public OrganisationService(OrganisationRepository organisationRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoderConfig) {
        this.organisationRepository = organisationRepository;
        this.userRepository = userRepository;
        this.passwordEncoderConfig = passwordEncoderConfig;
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

@Transactional
    public UserRepository addUser(UserRequestDTO request ){
        Organisation organisation = new OrganisationRepository
                .

}
}
