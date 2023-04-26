package com.example.magazin.controller;

import com.example.magazin.dto.coupon.CouponDto;
import com.example.magazin.service.CouponService;
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
    private CouponService couponService;

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
    @GetMapping("/products/{ids}/coupon/{coupon}")
    public String getCartWithCoupon(
            @PathVariable(name = "ids") String ids,
            @PathVariable(name = "coupon") String coupon,
            Model model){
        System.out.println(ids);
        System.out.println(coupon);
        List<Integer> idsList = Arrays.stream(ids.split(","))
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull).toList();
        idsList.forEach(System.out::println);
        model.addAttribute("products", productService.getProductsByIdList(idsList));
        CouponDto couponDto = couponService.getCouponByName(coupon);
        System.out.println(couponDto);
        model.addAttribute("coupon", couponDto);
        return "cart";
    }
}
