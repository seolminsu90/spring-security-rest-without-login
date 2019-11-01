package com.security.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.api.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findRoleIdByUserId(String userId);
}
