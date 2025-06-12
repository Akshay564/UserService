package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String username;
    private String email;
    private List<Role> roles;
}
