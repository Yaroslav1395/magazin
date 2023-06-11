package com.example.magazin.controller;

import com.example.magazin.dto.product.ProductForSingleDto;
import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.dto.review.ReviewForSaveDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.entity.user.Role;
import com.example.magazin.entity.user.User;
import com.example.magazin.service.ProductService;
import com.example.magazin.service.ReviewService;
import com.example.magazin.service.RoleService;
import com.example.magazin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/quantum/sProduct")
@AllArgsConstructor
public class SingleProductController {
    private ProductService productService;
    private ReviewService reviewService;
    private UserService userService;
    private RoleService roleService;
    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable Integer id, Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto user;
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        ProductForSingleDto productForSingleDto = productService.getProductById(id);
        int categoryId = productForSingleDto.getCategoryDto().getId();
        model.addAttribute("product", productForSingleDto);
        model.addAttribute("products", productService.getFourBestSellingProductsByCategory(categoryId));
        return "sproduct";
    }

    @GetMapping("/addComment")
    public String saveComment(
            @RequestParam String comment,
            @RequestParam Integer productId,
            Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
            if(productId != null && comment != null){
                reviewService.saveReview(ReviewForSaveDto.builder()
                                .userId(user.getId())
                                .productId(productId)
                                .dateTime(LocalDateTime.now())
                                .message(comment)
                        .build());
            }
        }

        ProductForSingleDto productForSingleDto = productService.getProductById(productId);
        int categoryId = productForSingleDto.getCategoryDto().getId();
        model.addAttribute("product", productForSingleDto);
        model.addAttribute("products", productService.getFourBestSellingProductsByCategory(categoryId));
        return "sproduct";
    }
}
