package com.example.magazin.controller;

import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.entity.user.Role;
import com.example.magazin.service.RoleService;
import com.example.magazin.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("quantum/blog")
public class BlogController {
    private UserService userService;
    private RoleService roleService;
    @GetMapping
    public String getBlog(SubscribeDto subscribeDto, Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        return "blog";
    }
}
