package com.example.usermanagement.dto.auditlogs;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AuditLogSearchRequestDto {

    @JsonProperty("organisation_shortcode")
    private String organisationShortcode;

    @NotNull
    @JsonProperty("start_date")
    private OffsetDateTime startDate;

    @NotNull
    @JsonProperty("end_date")
    private OffsetDateTime endDate;

    @JsonProperty("action_filter")
    private String actionFilter;

    @Min(1)
    private int page = 1;

    @Min(1)
    @Max(100)
    private int limit = 50;


}
