package org.example.common.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyInUseException extends CustomException {
    public EmailAlreadyInUseException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
