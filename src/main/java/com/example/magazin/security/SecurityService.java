package com.example.magazin.security;

import jakarta.servlet.http.HttpServletRequest;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password, HttpServletRequest request);
}
