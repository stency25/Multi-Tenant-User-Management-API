package com.example.usermanagement.service;


import com.example.usermanagement.dto.organisationdto.OrganisationRequestDto;
import com.example.usermanagement.entity.Organisation;
import com.example.usermanagement.repository.OrganisationRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganisationService {
    private final OrganisationRepository organisationRepository;
    @Autowired
    public OrganisationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }
@Transactional
    public Organisation createOrganisation(OrganisationRequestDto request) {

    if (organisationRepository.findByShortcode(request.getOrganisationShortCode()).isPresent()) {
        throw new DuplicateRequestException(
                "DUPLICATE SHORTCODE, AN ORGANISATION WITH THIS SHORTCODE ALREADY EXISTS");
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
}
