package com.example.usermanagement.service;


import com.example.usermanagement.dto.auditlogs.AuditLogFilterDTO;
import com.example.usermanagement.dto.auditlogs.AuditLogResponseDTO;
import com.example.usermanagement.entity.AuditLogEntity;
import com.example.usermanagement.repository.AuditLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }


    //Retrieves filtered and paginated audit logs for a specific tenant organisation.
    public Page<AuditLogResponseDTO> getFilteredAuditLogs(UUID organisationId, AuditLogFilterDTO filterDTO, Pageable pageable) {
        Page<AuditLogEntity> entityPage = auditLogRepository.findFilteredLogs(
                organisationId,
                filterDTO.getUserId(),
                filterDTO.getAction(),
                filterDTO.getStartDate(),
                filterDTO.getEndDate(),
                pageable
        );

        // Map database entities to response DTOs
        return entityPage.map(this::mapToResponseDTO);
    }

    //Helper method to map AuditLogEntity to AuditLogResponseDTO matching your DTO fields
    private AuditLogResponseDTO mapToResponseDTO(AuditLogEntity entity) {
        AuditLogResponseDTO dto = new AuditLogResponseDTO();
        dto.setId(entity.getId());
        dto.setOrganisationId(entity.getOrganisationId());
        dto.setUserId(entity.getUserId());
        dto.setUserType(entity.getUserType());
        dto.setAction(entity.getAction());
        dto.setResourceEndpoint(entity.getResourceEndpoint());
        dto.setIpAddress(entity.getIpAddress());
        dto.setUserAgent(entity.getUserAgent());
        dto.setRequestPayload(entity.getRequestPayload());
        dto.setResponseCode(entity.getResponseCode());
        dto.setTimeStamp(entity.getTimeStamp());
        return dto;
    }
}


