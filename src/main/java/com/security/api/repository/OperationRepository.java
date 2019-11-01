package com.security.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.api.domain.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findOperationByRoleSeq(Integer roleSeq);
}
