package com.example.magazin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quantum/shop")
@AllArgsConstructor
public class ShopController {
    @GetMapping()
    public String getShop(Model model){

        return "shop";
    }
}
