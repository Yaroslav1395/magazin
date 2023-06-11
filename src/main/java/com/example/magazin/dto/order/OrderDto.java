package com.example.magazin.dto.order;

import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Data time must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Past
    private LocalDateTime dateTime;
    @NotNull(message = "Total must be not null", groups = {OnUpdate.class, OnCreate.class})
    @DecimalMin(value = "1", message = "Price must be more then 1")
    @DecimalMax(value = "20000000", message = "Price must be smaller then 20 million")
    @Digits(integer = 20000000, fraction = 2, message = "Price must contain 2 symbols after point")
    private BigDecimal total;
    @NotNull(message = "Total must be not null", groups = {OnUpdate.class, OnCreate.class})
    private UserDto user;
    @NotEmpty(message = "List can not be null")
    private List<ProductForOrderDto> products = new ArrayList<>();
}
