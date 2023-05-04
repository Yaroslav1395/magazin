package com.example.magazin.dto.review;

import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@Builder
public class ReviewForSaveDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @NotBlank(message = "Massage must be not null and contain one symbol", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 500, message = "First name length must be smaller then 500", groups = {OnUpdate.class, OnCreate.class})
    private String message;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    @NotNull(message = "User id can to be null")
    @Min(value = 1)
    @Positive
    private Integer userId;
    @NotNull(message = "Product id can to be null")
    @Min(value = 1)
    @Positive
    private Integer productId;
}
