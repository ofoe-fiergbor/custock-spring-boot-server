package com.davinci.custockspringboot.util;


import com.davinci.custockspringboot.domain.model.auth.Role;
import com.davinci.custockspringboot.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleLoader {

    private final RoleService roleService;

    @Autowired
    public RoleLoader(RoleService roleService) {
        this.roleService = roleService;
    }

    public void createRoles() {
        roleService.write("USER");
        roleService.write("ADMIN");

        var allRoles = roleService.getAll();
        for (Role r: allRoles) {
            System.err.println(r.getName() +" authorization type loaded into database.");
        }
    }
}
