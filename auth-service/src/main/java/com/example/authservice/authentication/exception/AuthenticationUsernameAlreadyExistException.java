package com.example.authservice.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthenticationUsernameAlreadyExistException extends RuntimeException {
    public AuthenticationUsernameAlreadyExistException(String exception) {
        super(exception);
    }
}
