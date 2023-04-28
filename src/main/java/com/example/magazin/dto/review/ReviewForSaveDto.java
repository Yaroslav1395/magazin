package com.example.magazin.dto.review;

import com.example.magazin.dto.user.UserDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ReviewForSaveDto {
    private Integer id;
    private String message;

    private LocalDateTime dateTime;

    private Integer userId;
    private Integer productId;
}
