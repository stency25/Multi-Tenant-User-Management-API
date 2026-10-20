package com.example.usermanagement.dto.auditlogs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponseDTO {
    private UUID id;
    private UUID organisationId;
    private UUID userId;
    private String userType;
    private String action;
    private String resourceEndpoint;
    private String ipAddress;
    private String userAgent;
    private String requestPayload;
    private Integer responseCode;
    private OffsetDateTime timeStamp;




}
