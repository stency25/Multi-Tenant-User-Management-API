package com.example.usermanagement.repository;


import com.example.usermanagement.entity.AuditLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogEntity UUID> {
}
