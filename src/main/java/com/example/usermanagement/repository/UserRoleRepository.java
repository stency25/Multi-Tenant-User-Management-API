package com.example.usermanagement.repository;


import com.example.usermanagement.entity.RoleEntity;
import com.example.usermanagement.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRolesEntity, UUID> {

    List<UserRolesEntity>findByUser_Id(UUID UserId);
    Void deleteByUser_Id(UUID UserId);
}
