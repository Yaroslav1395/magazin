package com.example.magazin.dto.product;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.company.CompanyDto;
import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductForSingleDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String name;
    @NotNull(message = "Title must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 200, message = "Title length must be smaller then 200", groups = {OnUpdate.class, OnCreate.class})
    private String title;
    @NotNull(message = "Description must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 2000, message = "Description length must be smaller then 2000", groups = {OnUpdate.class, OnCreate.class})
    private String description;
    @NotNull(message = "Price must be not null", groups = {OnUpdate.class, OnCreate.class})
    @DecimalMin(value = "1", message = "Price must be more then 1")
    @DecimalMax(value = "2000000", message = "Price must be smaller then 2 million")
    @Digits(integer = 2000000, fraction = 2, message = "Price must contain 2 symbols after point")
    private BigDecimal price;
    @NotNull(message = "Products images mast be not null", groups = {OnUpdate.class, OnCreate.class})
    private List<ProductImageDto> productImagesDto;
    @NotNull(message = "Category mast be not null", groups = {OnUpdate.class, OnCreate.class})
    private CategoryDto categoryDto;
    @NotNull(message = "Company mast be not null", groups = {OnUpdate.class, OnCreate.class})
    private CompanyDto companyDto;
    private List<ReviewDto> reviewDtoList;
}
