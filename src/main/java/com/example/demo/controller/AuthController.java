package com.example.demo.controller;

import com.example.demo.authService.LoginRequest;
import com.example.demo.authService.LoginResponse;
import com.example.demo.authService.LogoutResponse;
import com.example.demo.feighnClient.AuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthController implements AuthClient {

        @PostMapping("/login")
        public LoginResponse login( @RequestBody LoginRequest request) {
            return login(request);
        }

        @PostMapping("/logout")
        public LogoutResponse logout(HttpServletRequest request) {
            return logout(request);
        }
    }


