package org.example.common.exceptions;

import org.springframework.http.HttpStatus;

public class WrongTokenException extends CustomException {
    public WrongTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
