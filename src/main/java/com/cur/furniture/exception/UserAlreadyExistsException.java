package com.cur.furniture.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(Long id) {
        super("User not found: " + id);
    }

}
