package com.example.magazin.dto.product;

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
@Data
@Builder
public class ProductForOrderDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String name;
    @NotNull(message = "Price must be not null", groups = {OnUpdate.class, OnCreate.class})
    @DecimalMin(value = "1", message = "Price must be more then 1")
    @DecimalMax(value = "2000000", message = "Price must be smaller then 2 million")
    @Digits(integer = 2000000, fraction = 2, message = "Price must contain 2 symbols after point")
    private BigDecimal price;
}
