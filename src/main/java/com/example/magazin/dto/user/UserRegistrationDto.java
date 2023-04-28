package com.example.magazin.dto.user;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
