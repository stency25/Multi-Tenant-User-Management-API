package com.example.usermanagement.service;


import com.example.usermanagement.dto.auditlogs.AuditLogPageResponseDto;
import com.example.usermanagement.dto.auditlogs.AuditLogSearchRequestDto;
import com.example.usermanagement.entity.AuditLogEntity;
import com.example.usermanagement.entity.Organisation;
import com.example.usermanagement.excemption.ResourceNotFoundException;
import com.example.usermanagement.repository.AuditLogRepository;
import com.example.usermanagement.repository.OrganisationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final OrganisationRepository organisationRepository;
    private final ObjectMapper objectMapper;

    public AuditLogService(AuditLogRepository auditLogRepository,
                           OrganisationRepository organisationRepository,
                           ObjectMapper objectMapper) {
        this.auditLogRepository = auditLogRepository;
        this.organisationRepository = organisationRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    public AuditLogPageResponseDto search(AuditLogSearchRequestDto request) {

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("end_date must not be before start_date");
        }

        UUID organisationId = null;
        String shortcode = request.getOrganisationShortcode();
        if (shortcode != null && !shortcode.isBlank()) {
            Organisation organisation = organisationRepository
                    .findByShortcode(shortcode)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "ORGANISATION_NOT_FOUND", "No organisation exists with this shortcode"));
            organisationId = organisation.getId();
        }

        String actionFilter = request.getActionFilter();
        if (actionFilter != null && actionFilter.isBlank()) {
            actionFilter = null;
        }

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());

        Page<AuditLogEntity> resultPage = auditLogRepository.search(
                organisationId, request.getStartDate(), request.getEndDate(), actionFilter, pageable);

        List<AuditLogPageResponseDto.Item> items = resultPage.getContent().stream()
                .map(this::toItem)
                .toList();

        AuditLogPageResponseDto.Pagination pagination = new AuditLogPageResponseDto.Pagination();
        pagination.setPage(request.getPage());
        pagination.setLimit(request.getLimit());
        pagination.setTotalRecords(resultPage.getTotalElements());

        AuditLogPageResponseDto response = new AuditLogPageResponseDto();
        response.setStatus("success");
        response.setPagination(pagination);
        response.setData(items);
        return response;
    }

    private AuditLogPageResponseDto.Item toItem(AuditLogEntity entity) {
        AuditLogPageResponseDto.Item item = new AuditLogPageResponseDto.Item();
        item.setAuditId(entity.getId());
        item.setTimestamp(entity.getTimeStamp());
        item.setUserId(entity.getUserId());
        item.setUserType(entity.getUserType());
        item.setAction(entity.getAction());
        item.setResourceEndpoint(entity.getResourceEndpoint());
        item.setIpAddress(entity.getIpAddress());
        item.setRequestPayload(parsePayload(entity.getRequestPayload()));
        item.setResponseCode(entity.getResponseCode());
        return item;
    }

    private JsonNode parsePayload(String payload) {
        if (payload == null || payload.isBlank()) {
            return null;
        }
        try {
            return objectMapper.readTree(payload);
        } catch (JsonProcessingException e) {
            return TextNode.valueOf(payload);
        }
    }
}






