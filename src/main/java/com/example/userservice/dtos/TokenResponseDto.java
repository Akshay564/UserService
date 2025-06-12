package com.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDto {
    private UserResponseDto user;
    private String value;
    private long expiryAt;
}
