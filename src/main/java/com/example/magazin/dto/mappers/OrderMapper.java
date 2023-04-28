package com.example.magazin.dto.mappers;

import com.example.magazin.dto.coupon.CouponDto;
import com.example.magazin.dto.order.OrderDto;
import com.example.magazin.entity.coupon.Coupon;
import com.example.magazin.entity.order.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);
    OrderDto toDto(Order order);
}
