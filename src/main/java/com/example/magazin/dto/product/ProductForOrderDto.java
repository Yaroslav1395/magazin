package com.example.magazin.dto.product;

import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import com.example.magazin.entity.productImage.ProductImage;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductForOrderDto implements Serializable {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String name;
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Min(value = 1)
    @Max(value = 20)
    private Integer categoryId;
    @NotNull(message = "Price must be not null", groups = {OnUpdate.class, OnCreate.class})
    @DecimalMin(value = "1", message = "Price must be more then 1")
    @DecimalMax(value = "2000000", message = "Price must be smaller then 2 million")
    @Digits(integer = 2000000, fraction = 2, message = "Price must contain 2 symbols after point")
    private BigDecimal price;
    @NotNull(message = "Amount must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Min(value = 1)
    private Integer amount;
    @NotNull(message = "Image must be not null", groups = {OnUpdate.class, OnCreate.class})
    private List<ProductImageDto> productImages;
}
