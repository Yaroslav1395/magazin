package com.example.magazin.service;

import com.example.magazin.dto.coupon.CouponDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.user.UserRegistrationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CouponService {
    boolean isCouponExist(Integer id);
    CouponDto getCouponById(Integer id);
    CouponDto getCouponByName(String name);
    List<CouponDto> getCouponsByIds(List<Integer> ids);
    List<CouponDto> getCouponsByNames(List<String> names);
    List<CouponDto> getAllCoupons();
    List<CouponDto> getAllCoupons(Sort sort);
    List<CouponDto> getAllCoupons(Pageable pageable);
    CouponDto saveCoupon(CouponDto couponDto);
    List<CouponDto> saveCoupons(List<CouponDto> couponDtoList);
    boolean deleteCouponById(Integer id);
    void deleteCouponsByIds(List<Integer> ids);
    CouponDto updateCoupon(Integer couponId, CouponDto couponDto);
    Long countCoupons();
}
