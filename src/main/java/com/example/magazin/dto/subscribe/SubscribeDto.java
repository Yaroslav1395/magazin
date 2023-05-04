package com.example.magazin.dto.subscribe;

import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    @Min(value = 1, message = "Id must be bigger then 1", groups = OnUpdate.class)
    private Integer id;
    @Email(message = "Email must be correspond bob@gmail.com", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, min = 10, message = "Email length must be smaller then 100 or bigger then 10", groups = {OnUpdate.class, OnCreate.class})
    private String email;
}
