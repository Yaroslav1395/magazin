package com.example.magazin.controller;

import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.service.serviceImpl.ProductServiceImpl;
import com.example.magazin.service.serviceImpl.SubscribeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/quantum/main")
@AllArgsConstructor
public class MainController {
    private ProductServiceImpl productService;
    private SubscribeServiceImpl subscribeService;

    @GetMapping()
    public String main(Model model){
        List<ProductForMainDto> recommendedProducts =
                productService.getMostExpensiveProductInEachCategoryWithLimitFour();
        List<ProductForMainDto> newProducts = productService.getFirst8ByOrderByReceiptDate();

        model.addAttribute("products", recommendedProducts);
        model.addAttribute("newProducts", newProducts);
        return "main";
    }

    @PostMapping()
    public String subscribeForNews(@RequestBody String email, Model model){
        subscribeService.createSubscribe(email);
        List<ProductForMainDto> recommendedProducts =
                productService.getMostExpensiveProductInEachCategoryWithLimitFour();
        List<ProductForMainDto> newProducts = productService.getFirst8ByOrderByReceiptDate();

        model.addAttribute("products", recommendedProducts);
        model.addAttribute("newProducts", newProducts);
        return "main";
    }

}
