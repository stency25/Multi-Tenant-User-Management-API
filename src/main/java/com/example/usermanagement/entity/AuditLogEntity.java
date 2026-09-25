package com.example.usermanagement.entity;


import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "Audit_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "organisation_id",nullable = true)
    private UUID organisationId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "user_type", nullable = false, length = 20)
    private String userType;

    @Column(name = "action", length = 100, nullable = false)
    private String action;

    @Column(name = "resource_endpoint", length = 255, nullable = false)
    private String resourceEndpoint;

    @Column(name = "ip_address", nullable = false,length = 45)
    private String ipAdress;

    @Column(name = "user_agemt",nullable = false,columnDefinition = "TEXT")
    private String user_Agent;

    @Column(name = "request_payload", nullable = true,columnDefinition = "JSONB")
    private String requestPayload;

    @Column(name = "response_code",columnDefinition = "INTEGER", nullable = false)
    private String responseCode;

    @CreationTimestamp
    @Column(name = "time_stamp",columnDefinition = "TIMESTAMP",nullable = false)
    private OffsetDateTime timeStamp;

}
