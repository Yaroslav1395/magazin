package com.example.magazin.dto.product;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.company.CompanyDto;
import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.dto.review.ReviewDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductForSingleDto {
    private Integer id;
    private String name;
    private String title;
    private String description;
    private BigDecimal price;
    private ProductImageDto productImageDto;
    private CategoryDto categoryDto;
    private CompanyDto companyDto;
    private List<ReviewDto> reviewDtoList;
}
