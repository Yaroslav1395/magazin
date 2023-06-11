package com.example.magazin.controller;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.user.Role;
import com.example.magazin.service.CategoryService;
import com.example.magazin.service.ProductService;
import com.example.magazin.service.RoleService;
import com.example.magazin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/quantum/shop")
@AllArgsConstructor
public class ShopController {
    private CategoryService categoryService;
    private ProductService productService;
    private UserService userService;
    private RoleService roleService;
    @GetMapping
    public String getShop(Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        Double maxProductPrice = productService.getMaxPriceInAllProducts();
        Double minProductPrice = productService.getMinPriceInAllProducts();

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("maxProductPrice", maxProductPrice);
        model.addAttribute("minProductPrice", minProductPrice);
        model.addAttribute("minInputValue", minProductPrice);
        model.addAttribute("maxInputValue", maxProductPrice / 2);
        model.addAttribute("keyword", "Ввод");
        return "shop";
    }

    @GetMapping("/category/{categoryId}")
    public String getProductsByCategoryId(
            @PathVariable Integer categoryId,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "sortDir") String sortDir,
            @RequestParam(name = "sortField") String sortField,
            Model model
    ){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        Pageable pageable = productService.createPageable(sortField, sortDir, pageNumber);
        Page<ProductForMainDto> products = productService.getAllProductsByCategoryId(pageable, categoryId);
        Double maxProductPrice = productService.getMaxProductPriceInCategory(categoryId);
        Double minProductPrice = productService.getMinProductPriceInCategory(categoryId);

        model.addAttribute("products", products);
        model.addAttribute("urlForPagination", "http://localhost:8080/quantum/shop/category/" + categoryId + "?sortDir=" + sortDir + "&sortField=" + sortField + "&");
        model.addAttribute("urlForPriceSortAsc", "http://localhost:8080/quantum/shop/category/" + categoryId + "?pageNumber="+ pageNumber + "&sortDir=asc&sortField=price");
        model.addAttribute("urlForPriceSortDesc", "http://localhost:8080/quantum/shop/category/" + categoryId + "?pageNumber="+ pageNumber + "&sortDir=desc&sortField=price");
        model.addAttribute("urlForNameSortDesc", "http://localhost:8080/quantum/shop/category/" + categoryId + "?pageNumber="+ pageNumber + "&sortDir=desc&sortField=name");
        model.addAttribute("urlForNameSortAsc", "http://localhost:8080/quantum/shop/category/" + categoryId + "?pageNumber="+ pageNumber + "&sortDir=asc&sortField=name");

        model.addAttribute("maxProductPrice", maxProductPrice);
        model.addAttribute("minProductPrice", minProductPrice);
        model.addAttribute("minInputValue", minProductPrice);
        model.addAttribute("maxInputValue", maxProductPrice / 2);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", categoryService.getCategoryById(categoryId).getName());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("categoryId", categoryId);
        return "shop";
    }

    @GetMapping("/category/{categoryId}/limit")
    public String getProductsByCategoryIdWithLimit(
            @PathVariable Integer categoryId,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "sortDir") String sortDir,
            @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "minInputValue") Double minInputValue,
            @RequestParam(name = "maxInputValue") Double maxInputValue,
            Model model) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        Pageable pageable = productService.createPageable(sortField, sortDir, pageNumber);
        Page<ProductForMainDto> products  = productService.getProductByCategoryIdWithPriceLimit(pageable, categoryId, minInputValue, maxInputValue);
        Double maxProductPrice = productService.getMaxProductPriceInCategory(categoryId);
        Double minProductPrice = productService.getMinProductPriceInCategory(categoryId);

        model.addAttribute("products", products);
        model.addAttribute("urlForPagination", "http://localhost:8080/quantum/shop/category/" + categoryId + "/limit?sortDir=" + sortDir + "&sortField=" + sortField + "&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue + "&");
        model.addAttribute("urlForPriceSortAsc", "http://localhost:8080/quantum/shop/category/" + categoryId + "/limit?pageNumber="+ pageNumber + "&sortDir=asc&sortField=price&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForPriceSortDesc", "http://localhost:8080/quantum/shop/category/" + categoryId + "/limit?pageNumber="+ pageNumber + "&sortDir=desc&sortField=price&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForNameSortDesc", "http://localhost:8080/quantum/shop/category/" + categoryId + "/limit?pageNumber="+ pageNumber + "&sortDir=desc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForNameSortAsc", "http://localhost:8080/quantum/shop/category/" + categoryId + "/limit?pageNumber="+ pageNumber + "&sortDir=asc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("maxProductPrice", maxProductPrice);
        model.addAttribute("minProductPrice", minProductPrice);
        model.addAttribute("minInputValue",minInputValue);
        model.addAttribute("maxInputValue", maxInputValue);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", "Ввод");
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("categoryId", categoryId);
        return "shop";
    }

    @GetMapping("/filter")
    public String getProductsByKeyword(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "sortDir") String sortDir,
            @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "minInputValue") Double minInputValue,
            @RequestParam(name = "maxInputValue") Double maxInputValue,
            Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        Pageable pageable = productService.createPageable(sortField, sortDir, pageNumber);
        Page<ProductForMainDto> products  = productService.getProductByKeyword(pageable, keyword);
        Double maxProductPrice = productService.getMaxProductPriceByKeyword(keyword);
        Double minProductPrice = productService.getMinProductPriceByKeyword(keyword);

        if(maxInputValue > maxProductPrice){
            maxProductPrice = maxInputValue;
        }

        model.addAttribute("products", products);

        model.addAttribute("urlForPagination", "http://localhost:8080/quantum/shop/filter?keyword=" + keyword + "&sortDir=" + sortDir + "&sortField=" + sortField + "&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue + "&");
        model.addAttribute("urlForPriceSortAsc", "http://localhost:8080/quantum/shop/filter?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=asc&sortField=price&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForPriceSortDesc", "http://localhost:8080/quantum/shop/filter?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=desc&sortField=price&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForNameSortDesc", "http://localhost:8080/quantum/shop/filter?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=desc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForNameSortAsc", "http://localhost:8080/quantum/shop/filter?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=asc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForPriceFilter", "http://localhost:8080/quantum/shop/filter/limit?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=asc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("maxProductPrice", maxProductPrice);
        model.addAttribute("minProductPrice", minProductPrice);
        model.addAttribute("minInputValue",minInputValue);
        model.addAttribute("maxInputValue", maxInputValue);

        model.addAttribute("keyword", keyword);
        model.addAttribute("pageNumber", pageNumber);

        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }

    @GetMapping("/filter/limit")
    public String getProductsByKeywordWithLimit(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "sortDir") String sortDir,
            @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "minInputValue") Double minInputValue,
            @RequestParam(name = "maxInputValue") Double maxInputValue,
            Model model
    ){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        Pageable pageable = productService.createPageable(sortField, sortDir, pageNumber);
        Page<ProductForMainDto> products  = productService.getProductByKeywordWithPriceLimit(pageable, keyword, minInputValue, maxInputValue);
        Double maxProductPrice = productService.getMaxProductPriceByKeyword(keyword);
        Double minProductPrice = productService.getMinProductPriceByKeyword(keyword);

        model.addAttribute("products", products);
        model.addAttribute("urlForPagination", "http://localhost:8080/quantum/shop/filter/limit?keyword=" + keyword + "&sortDir=" + sortDir + "&sortField=" + sortField + "&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue + "&");
        model.addAttribute("urlForPriceSortAsc", "http://localhost:8080/quantum/shop/filter/limit?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=asc&sortField=price&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForPriceSortDesc", "http://localhost:8080/quantum/shop/filter/limit?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=desc&sortField=price&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForNameSortDesc", "http://localhost:8080/quantum/shop/filter/limit?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=desc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForNameSortAsc", "http://localhost:8080/quantum/shop/filter/limit?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=asc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("urlForPriceFilter", "http://localhost:8080/quantum/shop/filter/limit?keyword=" + keyword + "&pageNumber="+ pageNumber + "&sortDir=asc&sortField=name&minInputValue=" + minInputValue + "&maxInputValue=" + maxInputValue);
        model.addAttribute("maxProductPrice", maxProductPrice);
        model.addAttribute("minProductPrice", minProductPrice);
        model.addAttribute("minInputValue",minInputValue);
        model.addAttribute("maxInputValue", maxInputValue);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }

    @PostMapping("/addProduct")
    public String addProductToCart(
            @RequestParam("productId") Integer productId,
            @RequestParam("currentUrl") String currentUrl,
            @RequestParam("productAmount") Integer productAmount,
            HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Set<ProductForOrderDto> productsForOrder = (Set<ProductForOrderDto>) request.getSession().getAttribute("SESSION_PRODUCT_ID");
        if (productsForOrder == null) {
            productsForOrder = new HashSet<>();
            request.getSession().setAttribute("SESSION_PRODUCT_ID", productsForOrder);
        }
        ProductForOrderDto productForOrderDto = productService.getProductForOrderById(productId);
        productForOrderDto.setAmount(productAmount);
        productsForOrder.add(productForOrderDto);
        request.getSession().setAttribute("SESSION_PRODUCT_ID", productsForOrder);
        return "redirect:" + currentUrl;
    }
}
