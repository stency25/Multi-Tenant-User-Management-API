package com.example.usermanagement.controller;


import com.example.usermanagement.dto.auditlogs.AuditLogFilterDTO;
import com.example.usermanagement.dto.auditlogs.AuditLogResponseDTO;
import com.example.usermanagement.service.AuditLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuditLogController {

    private final AuditLogService auditLogService;
    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    //Retrieve filtered and paginated audit logs for a specific tenant organisation.
    @GetMapping
    public ResponseEntity<Page<AuditLogResponseDTO>> getAuditLogs(
            @PathVariable UUID organisationId,
            @ModelAttribute AuditLogFilterDTO filterDTO,
            Pageable pageable) {
         /// Fetch filtered and paginated audit logs securely scoped to the organization
        Page<AuditLogResponseDTO> logs = auditLogService.getFilteredAuditLogs(organisationId, filterDTO, pageable);
        return ResponseEntity.ok(logs);
    }




}
