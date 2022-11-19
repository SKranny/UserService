package com.example.demo.feighnClient;



import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.LogoutResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "authClient", url = "https://localhost:8081/api/v1/auth")
public interface AuthClient {
    @PostMapping("/login")
     String login(LoginRequest request);

    @PostMapping("/logout")
    LogoutResponse logout(HttpServletRequest request);

}

