package com.atipera.recruitment_task.exception;

import org.springframework.web.HttpMediaTypeNotAcceptableException;

public class HeaderValueException extends HttpMediaTypeNotAcceptableException {

    public HeaderValueException(String string) {
        super(string);
    }
}
