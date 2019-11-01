package com.security.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.security.api.domain.Operation;
import com.security.api.domain.Role;
import com.security.api.domain.User;
import com.security.api.domain.UserDetailsDTO;
import com.security.api.repository.OperationRepository;
import com.security.api.repository.RoleRepository;
import com.security.api.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OperationRepository operationRepository;

    // Set UserDetails From DB
    public UserDetailsDTO readUser(String id) {
        User user = userRepository.findByUserId(id);
        if (user != null) {
            UserDetailsDTO userDto = new UserDetailsDTO();
            userDto.setAuthorities(getAuthoritiesFromRoles(user.getRoles()));
            userDto.setId(id);
            return userDto;
        } else {
            return null;
        }
    }

    // Set Authorities From DB
    public Collection<GrantedAuthority> getAuthorities(Integer userSeq) {
        List<Role> authoritiesList = roleRepository.findRoleIdByUserSeq(userSeq);
        List<GrantedAuthority> authorities = new ArrayList<>();

        authoritiesList.stream().forEach(role -> {
            List<Operation> operationList = operationRepository.findOperationByRoleSeq(role.getRoleSeq());
            operationList.stream().forEach(operation -> {
                authorities.add(new SimpleGrantedAuthority(operation.getAuthority()));
            });

            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        });

        return authorities;
    }

    // Set UserDetails From Token
    public UserDetailsDTO readUser(String id, List<String> authorities) {
        UserDetailsDTO userDto = new UserDetailsDTO();
        userDto.setAuthorities(getAuthoritiesFromToken(authorities));
        userDto.setId(id);
        return userDto;
    }

    // Set Authorities From Token
    public Collection<GrantedAuthority> getAuthoritiesFromToken(List<String> authorityList) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorityList.stream().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return authorities;
    }

    // Set Authorities From JPA Join User's Roles
    public Collection<GrantedAuthority> getAuthoritiesFromRoles(List<Role> roleList) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        roleList.stream().forEach(role -> {
            List<Operation> operationList = role.getOperations();
            operationList.stream().forEach(operation -> {
                authorities.add(new SimpleGrantedAuthority(operation.getAuthority()));
            });

            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        });

        return authorities;
    }
}
