package com.davinci.custockspringboot.domain.repository.auth;

import com.davinci.custockspringboot.domain.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
