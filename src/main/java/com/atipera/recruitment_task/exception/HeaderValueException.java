package com.atipera.recruitment_task.exception;

import org.springframework.web.HttpMediaTypeNotAcceptableException;

public class HeaderValueException extends RuntimeException {

    public HeaderValueException(String string) {
        super(string);
    }
}
