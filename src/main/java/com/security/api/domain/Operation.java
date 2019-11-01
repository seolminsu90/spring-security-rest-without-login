package com.security.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_OPERATION")
@JsonInclude(Include.NON_NULL)
public class Operation implements GrantedAuthority {
    @Id
    @Column(name = "OPERATION_SEQ")
    private Integer operationSeq;

    @Column(name = "ROLE_SEQ")
    private Integer roleSeq;

    @Column(name = "OPERATION_ID")
    private String operationId;

    @Override
    public String getAuthority() {
        return operationId;
    }
}
