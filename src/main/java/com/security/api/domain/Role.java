package com.security.api.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ROLE")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "USER_ID")
    private String userId;

    @OneToMany
    @JoinTable(name = "TB_OPERATION", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "OPERATION_ID") })
    private List<Operation> allowedOperations = new ArrayList<>();

    @Override
    public String getAuthority() {
        return roleId;
    }

    public Collection<GrantedAuthority> getAllowedOperations() {
        return null;
    }
}