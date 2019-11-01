package com.security.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.api.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findRoleIdByUserSeq(Integer userSeq);
}
