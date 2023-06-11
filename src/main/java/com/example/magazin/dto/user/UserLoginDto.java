package com.example.magazin.dto.user;

import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @Email(message = "Email must be correspond bob@gmail.com", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Email length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 200, message = "Password length must be smaller then 200", groups = {OnUpdate.class, OnCreate.class})
    private String password;
}
