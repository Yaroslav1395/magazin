package com.example.magazin.dto.company;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {
    private Integer id;

    private String name;

    private String image;
}
