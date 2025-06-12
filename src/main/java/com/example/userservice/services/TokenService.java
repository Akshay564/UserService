package com.example.userservice.services;

import com.example.userservice.dtos.TokenResponseDto;
import com.example.userservice.dtos.UserResponseDto;
import com.example.userservice.models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public TokenResponseDto generateToken(User user) {
        TokenResponseDto token = new TokenResponseDto();
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        token.setExpiryAt(System.currentTimeMillis() + 3600000);

        //Updating User details
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setRoles(user.getRoles());
        userResponseDto.setEmail(user.getEmail());

        token.setUser(userResponseDto);

        return token;
    }
}
