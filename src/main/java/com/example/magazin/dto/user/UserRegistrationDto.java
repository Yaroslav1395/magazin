package com.example.magazin.dto.user;


import com.example.magazin.dto.validation.OnCreate;
import com.example.magazin.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UserRegistrationDto {
    @NotBlank(message = "First name must be not null and contain one symbol", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "First name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String firstName;
    @NotBlank(message = "Last name must be not null and contain one symbol", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Last name length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String lastName;
    @Email(message = "Email must be correspond bob@gmail.com", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 100, message = "Email length must be smaller then 100", groups = {OnUpdate.class, OnCreate.class})
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 200, message = "Password length must be smaller then 200", groups = {OnUpdate.class, OnCreate.class})
    private String password;
}
