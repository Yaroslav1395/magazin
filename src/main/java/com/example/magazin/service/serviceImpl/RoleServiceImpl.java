package com.example.magazin.service.serviceImpl;

import com.example.magazin.entity.user.Role;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.role.RoleRepository;
import com.example.magazin.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    @Override
    public List<Role> findRoleByUserId(Integer userId) {
        return roleRepository.findRoleByUserId(userId);
    }


}
