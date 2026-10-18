package com.example.usermanagement.repository;


import com.example.usermanagement.entity.AuditLogEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogEntity, UUID> {


    //Finds audit logs strictly filtered by organisation ID.
    Page<AuditLogEntity> findByOrganisationId(UUID OrganisationId, Pageable pageable);

//Allows clients to optionally filter logs by user, action, or date range
    @Query("SELECT a FROM AuditLogEntity a WHERE a.organisationId = :OrganisationId " +
            "AND (:userId IS NULL OR a.userId = :userId) " +
            "AND (: action is NULL OR a .action =:action) " +
            "AND (cast(:startDate as timestamp) IS NULL OR a.timestamp >= :startDate)" +
            "AND (cast(:endDate as timestamp)IS NULL OR a .timestamp <= : endDate)")


    //* parameter permanently locks down results to a specific tenant (organisationId).
    Page<AuditLogEntity>findFilteredLogs(
            @Param("organisationId") UUID organisationId,
            @Param("userId") UUID userId,
            @Param("action") String action,
            @Param("startDate") OffsetDateTime startDate,
            @Param("endDate") OffsetDateTime endDate,

            Pageable pageable
    );

}