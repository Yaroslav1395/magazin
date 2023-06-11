package com.example.magazin.controller;

import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.entity.user.Role;
import com.example.magazin.service.RoleService;
import com.example.magazin.service.UserService;
import com.example.magazin.service.serviceImpl.ProductServiceImpl;
import com.example.magazin.service.serviceImpl.SubscribeServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/quantum/main")
@AllArgsConstructor
public class MainController {
    private ProductServiceImpl productService;
    private SubscribeServiceImpl subscribeService;
    private UserService userService;
    private RoleService roleService;

    @GetMapping
    public String main(SubscribeDto subscribeDto, Model model){
        List<ProductForMainDto> recommendedProducts =
                productService.getMostExpensiveProductInEachCategoryWithLimitFour();

        List<ProductForMainDto> newProducts = productService.getFirst8ByOrderByReceiptDate();

        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        model.addAttribute("products", recommendedProducts);
        model.addAttribute("newProducts", newProducts);
        return "main";
    }

    @PostMapping
    public String subscribeForNews(@ModelAttribute @Validated(OnCreate.class) SubscribeDto subscribeDto, BindingResult bindingResult, Model model){
        if(!subscribeService.existByEmail(subscribeDto.getEmail()) && !bindingResult.hasErrors()){
            subscribeService.saveSubscribe(subscribeDto.getEmail());
            model.addAttribute("message", "You subscribed to the news");
        }else{
            model.addAttribute("message", "This email is already signed or email consist exceptions");
        }

        List<ProductForMainDto> recommendedProducts =
                productService.getMostExpensiveProductInEachCategoryWithLimitFour();
        List<ProductForMainDto> newProducts = productService.getFirst8ByOrderByReceiptDate();

        model.addAttribute("products", recommendedProducts);
        model.addAttribute("newProducts", newProducts);
        return "main";
    }

}
