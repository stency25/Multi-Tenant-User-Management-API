package com.example.usermanagement.dto.auditlogs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogFilterDTO {
    private UUID organisationId;
    private UUID userId;
    private String action;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    // Pagination fields
    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;
}
