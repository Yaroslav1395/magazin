package com.example.magazin.dto.coupon;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CouponDto {
    private Integer id;
    private String name;
    private BigDecimal percent;
    private LocalDateTime activeUntil;
    private boolean active;
}
