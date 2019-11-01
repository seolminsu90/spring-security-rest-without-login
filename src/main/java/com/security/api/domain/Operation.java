package com.security.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "TB_OPERATION")
public class Operation implements GrantedAuthority {
    @Id
    @Column(name = "OPERATION_ID")
    private String operationId;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Override
    public String getAuthority() {
        return operationId;
    }
}
