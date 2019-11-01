package com.security.api.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @OneToMany
    @JoinTable(name = "TB_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private List<Role> roles;

    public Collection<Role> getRoles() {
        return roles;
    }
}
