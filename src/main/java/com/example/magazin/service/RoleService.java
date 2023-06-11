package com.example.magazin.service;

import com.example.magazin.entity.user.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role findByRole(String role);
    List<Role> findRoleByUserId(Integer userId);
}
