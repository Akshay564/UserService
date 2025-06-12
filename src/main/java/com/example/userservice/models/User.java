package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String email;

    private String username;

    private String hashedPassword;

    private Boolean isEmailVerified;

    @ManyToMany
    private List<Role> roles;
}
