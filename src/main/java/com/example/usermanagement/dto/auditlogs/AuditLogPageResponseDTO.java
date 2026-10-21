package com.example.usermanagement.dto.auditdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AuditLogPageResponseDto {

    private String status;
    private Pagination pagination;
    private List<Item> data;

    @Data
    public static class Pagination {
        private int page;
        private int limit;

        @JsonProperty("total_records")
        private long totalRecords;
    }

    @Data
    public static class Item {
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
}