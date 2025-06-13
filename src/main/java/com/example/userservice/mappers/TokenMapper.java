package com.example.userservice.mappers;

import com.example.userservice.dtos.TokenResponseDto;
import com.example.userservice.dtos.UserResponseDto;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {
    public TokenResponseDto toDto(Token tokenEntity, User user) {
        TokenResponseDto tokenDto = new TokenResponseDto();
        tokenDto.setValue(tokenEntity.getValue());
        tokenDto.setExpiryAt(tokenEntity.getExpiryAt());

        UserResponseDto userDto = new UserResponseDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        tokenDto.setUser(userDto);

        return tokenDto;
    }
}
