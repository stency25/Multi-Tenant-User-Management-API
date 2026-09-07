package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {

    Optional<Organisation> findByShortcode(String shortcode);

    boolean existsByShortcode(String shortcode);

    Optional<Organisation> findByEmail(String email);

    boolean existsByEmail(String email);
}
