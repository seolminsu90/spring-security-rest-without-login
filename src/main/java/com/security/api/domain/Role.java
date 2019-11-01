package com.security.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ROLE")
@JsonInclude(Include.NON_NULL)
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "ROLE_SEQ")
    private Integer roleSeq;

    @Column(name = "USER_SEQ")
    private Integer userSeq;

    @Column(name = "ROLE_ID")
    private String roleId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumnOrFormula(column = @JoinColumn(name = "ROLE_SEQ", referencedColumnName = "ROLE_SEQ"))
    private List<Operation> operations;

    @Override
    public String getAuthority() {
        return roleId;
    }
}