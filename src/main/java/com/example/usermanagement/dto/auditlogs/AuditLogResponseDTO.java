package com.example.usermanagement.dto.auditlogs;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponseDTO {

    @JsonProperty("audit_id")
    private UUID auditId;

    private OffsetDateTime timestamp;

    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("user_type")
    private String userType;

    private String action;

    @JsonProperty("resource_endpoint")
    private String resourceEndpoint;

    @JsonProperty("ip_address")
    private String ipAddress;

    @JsonProperty("request_payload")
    private JsonNode requestPayload;

    @JsonProperty("response_code")
    private Integer responseCode;
}




