package com.example.magazin.dto.product;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.productImageDto.ProductImageDto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductForMainDto {

    private Integer id;

    private String name;

    private String title;

    private BigDecimal price;

    private ProductImageDto productImageDto;

    private CategoryDto categoryDto;
}
