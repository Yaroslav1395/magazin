package com.example.magazin.dto.order;

import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class OrderForSave {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Data time must be not null")
    @Past
    private LocalDateTime dateTime;
    @NotNull(message = "Total must be not null", groups = {OnUpdate.class, OnCreate.class})
    @DecimalMin(value = "1", message = "Price must be more then 1")
    @DecimalMax(value = "20000000", message = "Price must be smaller then 20 million")
    @Digits(integer = 20000000, fraction = 2, message = "Price must contain 2 symbols after point")
    private BigDecimal total;
    @NotNull(message = "User id can to be null")
    @Min(value = 1)
    @Positive
    private Integer userId;
    @NotEmpty(message = "List can not be null")
    private List<ProductForOrderDto> products = new ArrayList<>();
}
