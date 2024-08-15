package com.jay.employee.exception;

import java.io.Serializable;

public class UserNotFoundException extends RuntimeException implements Serializable {
    public UserNotFoundException(String id) {
        super("User with id" + id + "not found");
    }
}
