package com.example.userservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomError {
    private String message;
    private int status;
}
