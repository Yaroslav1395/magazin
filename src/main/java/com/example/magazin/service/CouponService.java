package com.example.magazin.service;

import com.example.magazin.dto.coupon.CouponDto;

import java.util.List;

public interface CouponService {
    boolean exists(CouponDto couponDto);
    CouponDto getById(Integer id);
    List<CouponDto> getAllById(List<Integer> ids);
    CouponDto save(CouponDto couponDto);
    List<CouponDto> saveAll(List<CouponDto> couponDtos);
    boolean delete(CouponDto couponDto);

    CouponDto getCouponByName(String name);
}
