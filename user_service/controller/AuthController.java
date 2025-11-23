package com.swiggylite.user_service.controller;

import com.swiggylite.user_service.dto.LoginRequest;
import com.swiggylite.user_service.dto.UserDto;
import com.swiggylite.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginRequest request){
        UserDto user = userService.authenticate(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(user);
    }
}
