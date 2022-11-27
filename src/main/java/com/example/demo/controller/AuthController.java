package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.feighnClient.AuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthClient client;
    @PostMapping("/test")
    String login(@RequestBody LoginRequest request){
        return client.login(request);
    }
}
