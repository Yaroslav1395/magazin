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
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        model.addAttribute("keyword", "Ввод");
        return "shop";
    }

    @GetMapping("/category/{categoryId}")
    public String getProductsByCategoryId(
            @PathVariable Integer categoryId,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "sortDir") String sortDit,
            @RequestParam(name = "sortField") String sortField,
            Model model) {

        Sort sort = Sort.by(sortField);
        sort = sortDit.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber, 6, sort);

        Page<ProductForMainDto> product = productService.getAllProductsByCategoryId(pageable, categoryId);


        model.addAttribute("products", product);
        model.addAttribute("url", "http://localhost:8080/quantum/shop/category/"
                        + categoryId + "?sortDir=" + sortDit + "&sortField=" + sortField + "&");
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", "Ввод");
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("categoryId", product.getContent().get(0).getCategoryDto().getId());
        return "shop";
    }

    @GetMapping("/filter")
    public String getProductsByKeyword(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "sortDir") String sortDit,
            @RequestParam(name = "sortField") String sortField,
            Model model){

        Sort sort = Sort.by(sortField);
        sort = sortDit.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber, 6, sort);

        Page<ProductForMainDto> products  = productService.getProductByKeyword(pageable, keyword);


        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute(
                "url", "http://localhost:8080/quantum/shop/filter?keyword=" + keyword + "&");
        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }
}
