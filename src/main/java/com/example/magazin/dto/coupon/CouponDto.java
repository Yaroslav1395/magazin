package com.example.magazin.dto.coupon;

import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CouponDto implements Serializable {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String name;
    @NotNull(message = "Percent must be not null", groups = {OnUpdate.class, OnCreate.class})
    @DecimalMin(value = "1", message = "Price must be more then 1")
    @DecimalMax(value = "2000000", message = "Price must be smaller then 2 million")
    @Digits(integer = 2000000, fraction = 2, message = "Price must contain 2 symbols after point")
    private BigDecimal percent;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Data time must be not null")
    @Future(message = "Data must be future then now")
    private LocalDateTime activeUntil;
    private boolean active;
}
