package com.example.authservice.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthenticationIdAlreadyExistException extends RuntimeException {
    public AuthenticationIdAlreadyExistException(String exception) {
        super(exception);
    }
}
