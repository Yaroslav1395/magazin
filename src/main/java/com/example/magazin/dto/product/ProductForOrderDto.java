package com.example.magazin.dto.product;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class ProductForOrderDto {
    private Integer id;
    private String name;
    private BigDecimal price;
}
