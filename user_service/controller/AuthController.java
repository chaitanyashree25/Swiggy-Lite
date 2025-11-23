package com.swiggylite.user_service.controller;

import com.swiggylite.user_service.dto.LoginRequest;
import com.swiggylite.user_service.dto.TokenResponse;
import com.swiggylite.user_service.dto.UserDto;
import com.swiggylite.user_service.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Login endpoint: verifies credentials and returns a JWT token on success.
     * Request: { "email": "...", "password": "..." }
     * Response: { "token": "...", "tokenType": "Bearer" }
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request){
        // authenticate returns UserDto on success or throws IllegalArgumentException on invalid credentials
        UserDto user = userService.authenticate(request.getEmail(),request.getPassword());

        // create a token subject — using email keeps it simple; you can use user id if preferred
        String token = jwtUtil.generateToken(user.getEmail());

        TokenResponse response = new TokenResponse(token);
        return ResponseEntity.ok(response);
    }
}
