package com.example.magazin.controller;

import com.example.magazin.dto.user.UserLoginDto;
import com.example.magazin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/quantum/login")
public class LoginController {
    private UserService userService;

    @GetMapping
    public String getLogin(UserLoginDto userLoginDto, Model model){
        return "login";
    }

    @GetMapping("error")
    public String getLogin(@RequestParam boolean error, UserLoginDto userLoginDto, Model model){
        if(error){
            model.addAttribute("message", "Ввели неверный пароль");
        }
        return "login";
    }


}
