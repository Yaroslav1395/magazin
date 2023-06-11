package com.example.magazin.controller;

import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.dto.user.UserRegistrationDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.security.SecurityService;
import com.example.magazin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/quantum/registration")
public class RegistrationController {
    private UserService userService;
    private SecurityService securityService;

    @GetMapping
    public String getRegistration(UserRegistrationDto userRegistrationDto){
        return "registration";
    }

    @PostMapping
    public String saveUser(
            @ModelAttribute @Validated(OnCreate.class) UserRegistrationDto userRegistrationDto,
            SubscribeDto subscribeDto,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request){

        if(!userService.existsByEmail(userRegistrationDto.getEmail()) && !bindingResult.hasErrors()){
            UserRegistrationDto saveUser = userService.saveUser(userRegistrationDto);
            securityService.autoLogin(userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), request);
            model.addAttribute("user", saveUser);
            model.addAttribute("message", "Registration successful");
            return "cart";
        }else{
            model.addAttribute("message", "This email already exists or errors in data");
        }
        return "registration";
    }
}
