package com.example.magazin.controller;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.service.CategoryService;
import com.example.magazin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/quantum/shop")
@AllArgsConstructor
public class ShopController {
    private CategoryService categoryService;
    private ProductService productService;
    @GetMapping()
    public String getShop(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }
    @GetMapping("/category/{id}")
    public String getProductsByCategoryId(@PathVariable Integer id, Model model){
        List<ProductForMainDto> products = productService.getAllProductsByCategoryId(id);
        products.forEach(System.out::println);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }
}
