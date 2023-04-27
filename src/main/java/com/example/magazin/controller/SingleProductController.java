package com.example.magazin.controller;

import com.example.magazin.dto.product.ProductForSingleDto;
import com.example.magazin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quantum/sProduct")
@AllArgsConstructor
public class SingleProductController {
    private ProductService productService;
    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable Integer id, Model model){
        ProductForSingleDto productForSingleDto = productService.getProductById(id);
        int categoryId = productForSingleDto.getCategoryDto().getId();
        model.addAttribute("product", productForSingleDto);
        model.addAttribute("products", productService.getFourBestSellingProductsByCategory(categoryId));
        return "sproduct";
    }
}
