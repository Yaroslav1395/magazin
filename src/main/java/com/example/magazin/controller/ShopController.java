package com.example.magazin.controller;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.service.CategoryService;
import com.example.magazin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

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
    public String getProductsByCategoryId(
            @PathVariable Integer id,
            @RequestParam(name = "products") Integer pageNumber,
            Model model){

        Sort sortBy = Sort.by(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(pageNumber, 6, sortBy);
        Page<ProductForMainDto> product = productService.getAllProductsByCategoryId(pageable, id);

        model.addAttribute("products", product);
        model.addAttribute("url", "http://localhost:8080/quantum/shop/category/" + id);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }
}
