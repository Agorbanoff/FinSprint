package org.example.common.exceptions;

import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends CustomException {
    public ExpiredTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
