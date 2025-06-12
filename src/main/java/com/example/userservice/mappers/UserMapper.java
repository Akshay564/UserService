package com.example.userservice.mappers;

import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.UserResponseDto;
import com.example.userservice.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    private final BCryptPasswordEncoder passwordEncoder;
    public UserMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public User toEntity(SignUpRequestDto signUpRequestDto) {
        User user = new User();

        user.setEmail(signUpRequestDto.getEmail());
        user.setUsername(signUpRequestDto.getName());
        user.setHashedPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setIsEmailVerified(false);

        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setRoles(user.getRoles());

        return userResponseDto;
    }
}
