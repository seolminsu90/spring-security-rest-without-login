package com.security.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.api.domain.Operation;

public interface OperationRepository extends JpaRepository<Operation, String> {
    List<Operation> findOperationByRoleId(String roleId);
}
