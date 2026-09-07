package com.example.usermanagement.repository;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByOrganisationId(UUID organisationId);

    List<User> findByOrganisation_Shortcode(String shortcode);

    List<User> findByUserType(UserType userType);

    List<User> findByIsActive(boolean isActive);
}
