package com.swiggylite.user_service.service;

import com.swiggylite.user_service.domain.User;
import com.swiggylite.user_service.dto.UserDto;
import com.swiggylite.user_service.mapper.UserMapper;
import com.swiggylite.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

   private final UserMapper userMapper;

   private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDto registerUser(UserDto userDto,String rawPassword){

       User user =  userMapper.userResponse(userDto);
       user.setPasswordHash(passwordEncoder.encode(rawPassword));
        User saved = userRepository.save(user);
        return userMapper.userDtoResponse(saved);
    }


    public UserDto findById(Long id){
         Optional<User> user =  userRepository.findById(id);
         User user1 = user.orElseThrow(() -> new NoSuchElementException("User not found with this id"));
        return userMapper.userDtoResponse(user1);
    }


    public UserDto findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        User user1 = user.orElseThrow(() -> new NoSuchElementException("The user with the given email id is not present"));
       return userMapper.userDtoResponse(user1);
    }

    public UserDto authenticate(String email, String rawPassword){
        Optional<User> opt = userRepository.findByEmail(email);
        User user = opt.orElseThrow(() -> new IllegalArgumentException("Invaid credentials"));

        boolean matches = passwordEncoder.matches(rawPassword,user.getPasswordHash());
        if(!matches){
            throw new IllegalArgumentException("Invalid credentials");
        }

        return userMapper.userDtoResponse(user);
    }
}
