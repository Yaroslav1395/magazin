package com.example.magazin.dto.subscribe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscribeDto {
    private Integer id;
    private String email;
}
