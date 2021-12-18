package com.davinci.custockspringboot.service.auth;

import com.davinci.custockspringboot.domain.model.auth.Role;
import com.davinci.custockspringboot.domain.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void write(String name) {
        Role role = new Role(name);
        roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
