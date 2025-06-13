package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.TokenResponseDto;
import com.example.userservice.dtos.UserResponseDto;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.models.User;
import com.example.userservice.services.TokenService;
import com.example.userservice.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    public UserController(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    @PostMapping("signup")
    public UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.signUp(signUpRequestDto);
        return userMapper.toDto(user);
    }

    @PostMapping("login")
    public TokenResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @GetMapping("validate/{token}")
    public UserResponseDto validateToken(@PathVariable String token) {
        User user = tokenService.validateToken(token);
        return userMapper.toDto(user);
    }
}
