package com.swiggylite.user_service.controller;

import com.swiggylite.user_service.dto.UserDto;
import com.swiggylite.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public UserDto registerUser(@Valid @RequestBody UserDto userDto,@RequestParam String rawPassword){
        return userService.registerUser(userDto,rawPassword);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("by-email")
    public UserDto findByEmail(@RequestParam String email){
        return userService.findByEmail(email);
    }



}
