package com.example.magazin.dto.order;

import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private Integer id;

    private LocalDateTime dateTime;

    private BigDecimal total;
    private UserDto user;
    private List<ProductForOrderDto> products = new ArrayList<>();
}
