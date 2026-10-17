package com.example.usermanagement.repository;


import com.example.usermanagement.entity.AuditLogEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.UUID;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogEntity, UUID> {


    Page<AuditLogEntity> findByOrganisationId(UUID OrganisationId, Pageable pageable)
}
