package com.example.userservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomModelNotFoundException extends Exception {
    private  int id;

    public CustomModelNotFoundException(int id, String message) {
        super(message);
        this.id = id;
    }
}
