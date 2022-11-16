package com.example.demo.feighnClient;


import com.example.demo.authService.LoginRequest;
import com.example.demo.authService.LoginResponse;
import com.example.demo.authService.LogoutResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "authClient", url = "https://localhost:8081/")
public interface AuthClient {
    @PostMapping("/login")
    public LoginResponse login( @RequestBody LoginRequest request);

    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletRequest request);

}

