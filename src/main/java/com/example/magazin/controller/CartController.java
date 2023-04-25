package com.example.magazin.controller;

import com.example.magazin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/quantum/cart")
@AllArgsConstructor
public class CartController {
    private ProductService productService;

    @GetMapping("/products/{ids}")
    public String getCartWithProducts(@PathVariable(name = "ids") String ids, Model model){
        List<Integer> idsList = Arrays.stream(ids.split(","))
                .map(s -> {
                        try {
                            return Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    })
                .filter(Objects::nonNull).toList();

        model.addAttribute("products", productService.getProductsByIdList(idsList));
        return "cart";
    }
    @GetMapping()
    public String getCart(){
        return "cart";
    }
}
