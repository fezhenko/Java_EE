package org.example.swagger.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
