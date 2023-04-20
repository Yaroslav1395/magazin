package com.example.magazin.dto.productImageDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageDto {
    private Integer id;

    private String imageOne;

    private String imageTwo;

    private String imageThree;

    private String imageFour;
}
