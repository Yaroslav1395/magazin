package com.example.magazin.dto.mappers;

import com.example.magazin.dto.coupon.CouponDto;
import com.example.magazin.entity.coupon.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    Coupon toEntity(CouponDto couponDto);
    CouponDto toDto(Coupon coupon);
}
