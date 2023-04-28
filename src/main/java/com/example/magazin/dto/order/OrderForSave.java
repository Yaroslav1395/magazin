package com.example.magazin.dto.order;

import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.user.UserDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class OrderForSave {
    private Integer id;

    private LocalDateTime dateTime;

    private BigDecimal total;
    private Integer userId;
    private List<ProductForOrderDto> products = new ArrayList<>();
}
