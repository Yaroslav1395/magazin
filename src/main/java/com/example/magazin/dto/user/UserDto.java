package com.example.magazin.dto.user;

import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UserDto {
    @NotNull(message = "ID must be not null", groups = OnUpdate.class)
    private Integer id;
    @NotBlank(message = "First name must be not null and contain one symbol", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "First name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String firstName;
    @NotBlank(message = "Last name must be not null and contain one symbol", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "First name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String lastName;
    @Email(message = "Email must be correspond bob@gmail.com", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Email length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String email;
}
