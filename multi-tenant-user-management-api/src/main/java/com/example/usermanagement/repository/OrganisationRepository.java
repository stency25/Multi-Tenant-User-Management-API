package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {
    Optional<Organisation> findByShortcode(String shortcode);
}