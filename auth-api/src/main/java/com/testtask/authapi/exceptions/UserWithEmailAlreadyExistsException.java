package com.testtask.authapi.exceptions;

public class UserWithEmailAlreadyExistsException extends RuntimeException {
    public UserWithEmailAlreadyExistsException(String message) {
        super(message);
    }
}
