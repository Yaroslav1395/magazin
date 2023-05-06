package com.example.magazin.dto.productImageDto;

import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class ProductImageDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;

    @Length(max = 500, min=17, message = "Image one length must be more then 17 and smaller then 500 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    private String imageOne;
    @Length(max = 500, min=17, message = "Image two length must be more then 17 and smaller then 500 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    @NotNull(message = "Image path must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String imageTwo;
    @Length(max = 500, min=17, message = "Image three length must be more then 17 and smaller then 500 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    @NotNull(message = "Image path must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String imageThree;
    @Length(max = 500, min=17, message = "Image four length must be more then 17 and smaller then 500 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    @NotNull(message = "Image path must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String imageFour;
}
