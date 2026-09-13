package com.example.usermanagement.service;


import com.example.usermanagement.dto.Role.RoleResponseDto;
import com.example.usermanagement.entity.RoleEntity;
import com.example.usermanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    //constructor
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //FETCHING ALl  active roles and convert to dtO respones
    public List<RoleResponseDto>getRoleForTenant(String organisationShortCode){
        List < RoleEntity> activeRoles = roleRepository.findByIsActiveTrue();//FETH ROLE FROM DB
        return activeRoles.stream()
                //map entity obects into dtO OBJCTS
                .map(role-> new RoleResponseDto(
                        role.getId(),
                        role.getName(),
                        role.getDescription()
                ))
                        .collect(Collectors.toList());
    }


}
