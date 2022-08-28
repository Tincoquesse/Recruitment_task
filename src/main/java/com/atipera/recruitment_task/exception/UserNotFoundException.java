package com.atipera.recruitment_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UserNotFoundException extends HttpClientErrorException {

    public UserNotFoundException(HttpStatus status) {
        super(status);
    }
}

