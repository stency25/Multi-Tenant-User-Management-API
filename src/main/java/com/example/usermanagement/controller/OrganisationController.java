package com.example.usermanagement.controller;


import com.example.usermanagement.dto.ApiResponseDto;
import com.example.usermanagement.dto.organisationdto.OrganisationCreationResponseDto;
import com.example.usermanagement.dto.organisationdto.OrganisationRequestDto;
import com.example.usermanagement.dto.organisationdto.OrganisationResponseDto;
import com.example.usermanagement.entity.Organisation;
import com.example.usermanagement.service.OrganisationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/system/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;
@Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }
@PostMapping
    public ResponseEntity<ApiResponseDto<OrganisationCreationResponseDto>> createOrganisation(@Valid @RequestBody OrganisationRequestDto request ){

    Organisation saveOrganisation = organisationService.createOrganisation(request);

    OrganisationCreationResponseDto responseData = new OrganisationCreationResponseDto();
    responseData.setOrganisationId(saveOrganisation.getId());
    responseData.setOrganisationShortcode(saveOrganisation.getShortcode());
    responseData.setEmail(saveOrganisation.getEmail());

    ApiResponseDto<OrganisationCreationResponseDto> response = new ApiResponseDto<>();
    response.setStatus("successs");
    response.setMessage("organisation succesfully provides");
    response.setData(responseData);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);


}
}
