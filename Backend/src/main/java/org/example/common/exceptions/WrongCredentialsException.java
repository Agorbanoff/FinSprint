package org.example.common.exceptions;

import org.springframework.http.HttpStatus;

public class WrongCredentialsException extends CustomException {
    public WrongCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
