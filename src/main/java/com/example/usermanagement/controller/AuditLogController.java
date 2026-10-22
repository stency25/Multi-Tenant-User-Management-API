package com.example.usermanagement.controller;


import com.example.usermanagement.service.AuditLogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/audit")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping("/logs")
    public ResponseEntity<com.example.usermanagement.dto.auditdto.AuditLogPageResponseDto> searchLogs(
            @Valid @giRequestBody com.example.usermanagement.dto.auditdto.AuditLogSearchRequestDto request) {

        return ResponseEntity.ok(auditLogService.search(request));
    }
}
}
