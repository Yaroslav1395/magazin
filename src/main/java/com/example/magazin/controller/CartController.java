package com.example.magazin.controller;

import com.example.magazin.dto.coupon.CouponDto;
import com.example.magazin.dto.order.OrderDto;
import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.entity.user.Role;
import com.example.magazin.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/quantum/cart")
@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private CouponService couponService;
    private UserService userService;
    private RoleService roleService;
    private OrderService orderService;

    @GetMapping
    public String getCart(
            @RequestParam(value = "successful",required = false) boolean successful,
            SubscribeDto subscribeDto,
            Model model,
            HttpSession session){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        @SuppressWarnings("unchecked")
        Set<ProductForOrderDto> productsForOrderDto = (Set<ProductForOrderDto>) session.getAttribute("SESSION_PRODUCT_ID");
        if(productsForOrderDto != null){
            model.addAttribute("products", productsForOrderDto);
        }

        CouponDto couponDto = (CouponDto) session.getAttribute("coupon");
        if(couponDto != null){
            model.addAttribute("coupon", productsForOrderDto);
        }

        if(successful){
            model.addAttribute("message", "You have successful login");
        }
        return "cart";
    }
    @GetMapping("/productDelete")
    public String productDelete(
            @RequestParam(name = "productId") Integer productId,
            SubscribeDto subscribeDto,
            HttpSession session,
            Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        @SuppressWarnings("unchecked")
        Set<ProductForOrderDto> productsForOrderDto = (Set<ProductForOrderDto>) session.getAttribute("SESSION_PRODUCT_ID");

        if(productsForOrderDto != null){
            productsForOrderDto.forEach(System.out::println);
            productsForOrderDto.removeIf(productForOrderDto -> productForOrderDto.getId().equals(productId));
            productsForOrderDto.forEach(System.out::println);
            session.setAttribute("SESSION_PRODUCT_ID", productsForOrderDto);
            model.addAttribute("products", productsForOrderDto);
        }
        return "cart";
    }
    @GetMapping("/changeAmount")
    public String changeAmount(
            @RequestParam(name = "productId") Integer productId,
            @RequestParam(name = "amountProduct") Integer amountProduct,
            SubscribeDto subscribeDto,
            HttpSession session,
            Model model){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        @SuppressWarnings("unchecked")
        Set<ProductForOrderDto> productsForOrderDto = (Set<ProductForOrderDto>) session.getAttribute("SESSION_PRODUCT_ID");
        productsForOrderDto.forEach(productForOrderDto -> {
            if(productForOrderDto.getId().equals(productId)){
                productForOrderDto.setAmount(amountProduct);
            }
        });
        session.setAttribute("SESSION_PRODUCT_ID", productsForOrderDto);
        return "redirect:/quantum/cart";
    }

    @PostMapping("/newOrder")
    public String createOrder(
            @RequestParam(name = "total") BigDecimal total,
            Model model,
            HttpSession session){

        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        @SuppressWarnings("unchecked")
        Set<ProductForOrderDto> productsForOrderDtoSet = (Set<ProductForOrderDto>) session.getAttribute("SESSION_PRODUCT_ID");

        if(productsForOrderDtoSet != null && currentUserName != null){
            CouponDto couponDto = (CouponDto) session.getAttribute("coupon");
            if(couponDto != null){
                session.removeAttribute("coupon");
                couponService.deleteCouponById(couponDto.getId());
            }
            OrderDto orderDto = orderService.saveOrder(productsForOrderDtoSet, currentUserName, total);
            if(orderDto != null){
                model.addAttribute("order", orderDto);
            }else {
                model.addAttribute("message", "Order not save");
                return "redirect:/quantum/cart";
            }
        }
        return "order";
    }

    @PostMapping()
    public String getCartWithCoupon(
            @RequestParam(name = "coupon") String coupon,
            Model model,
            HttpSession session,
            SubscribeDto subscribeDto){

        final  String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(currentUserName != null && !currentUserName.equals("anonymousUser")){
            UserDto user = userService.getUserByEmail(currentUserName);
            List<Role> currentUserRoles = roleService.findRoleByUserId(user.getId());
            model.addAttribute("userRoles", currentUserRoles);
            model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        }

        CouponDto couponDto = couponService.getCouponByName(coupon);
        if(couponDto != null && couponDto.isActive()){
            session.setAttribute("coupon", couponDto);
            model.addAttribute("message", "Вы использовали купон. Скидка " + couponDto.getPercent());
            model.addAttribute("coupon", couponDto);
        };

        if (couponDto != null && !couponDto.isActive() ) {
            model.addAttribute("message", "Срок действия купона закончился " + couponDto.getActiveUntil());
        };

        if(couponDto == null){
            model.addAttribute("message", "Такого купона нет");
        };

        @SuppressWarnings("unchecked")
        Set<ProductForOrderDto> productsForOrderDto = (Set<ProductForOrderDto>) session.getAttribute("SESSION_PRODUCT_ID");
        if(productsForOrderDto != null){
            model.addAttribute("products", productsForOrderDto);
        }

        return "cart";
    }
}
