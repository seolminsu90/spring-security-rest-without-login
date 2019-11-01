package com.security.api.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER")
@JsonInclude(Include.NON_NULL)
public class User implements Serializable {
    @Id
    @Column(name = "USER_SEQ")
    private Integer userSeq;

    @Column(name = "USER_ID")
    private String userId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumnOrFormula(column = @JoinColumn(name = "USER_SEQ", referencedColumnName = "USER_SEQ"))
    private List<Role> roles;
}
