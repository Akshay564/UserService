package com.example.userservice.services;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.TokenResponseDto;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.models.User;
import com.example.userservice.repos.UserRepo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User signUp(SignUpRequestDto signUpRequestDto) {
        userRepo.findByEmail(signUpRequestDto.getEmail()).ifPresent(u-> {throw new IllegalArgumentException("User already exists!");});
        return userRepo.save(userMapper.toEntity(signUpRequestDto));
    }

    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<User> optionalUser = userRepo.findByEmail(loginRequestDto.getEmail());

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with the email: " + loginRequestDto.getEmail());
        }

        User user = optionalUser.get();

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getHashedPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return tokenService.generateToken(user);
    }
}
