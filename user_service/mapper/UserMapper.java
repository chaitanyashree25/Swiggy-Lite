package com.swiggylite.user_service.mapper;

import com.swiggylite.user_service.domain.User;
import com.swiggylite.user_service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public UserDto userDtoResponse(User user){
        if(user == null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        return userDto;
    }

    public User userResponse(UserDto userDto){
        if(userDto == null){
            return null;
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        return user;
    }


}
