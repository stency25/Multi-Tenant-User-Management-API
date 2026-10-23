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

    @Query("SELECT a FROM AuditLogEntity a " +
            "WHERE (:organisationId IS NULL OR a.organisationId = :organisationId) " +
            "AND a.timeStamp >= :startDate " +
            "AND a.timeStamp <= :endDate " +
            "AND (:actionFilter IS NULL OR a.action = :actionFilter) " +
            "ORDER BY a.timeStamp DESC")
    Page<AuditLogEntity> search(
            @Param("organisationId") UUID organisationId,
            @Param("startDate") OffsetDateTime startDate,
            @Param("endDate") OffsetDateTime endDate,
            @Param("actionFilter") String actionFilter,
            Pageable pageable);
}
