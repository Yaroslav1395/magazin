package com.example.magazin.dto.company;

import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class CompanyDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String name;
    @Length(max = 500, min=17, message = "Image length must be more then 17 and smaller then 500 symbols",
            groups = {OnUpdate.class, OnCreate.class})
    @NotNull(message = "Image path must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String image;
}
