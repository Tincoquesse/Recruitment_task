package com.atipera.recruitment_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(404, exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}

class ErrorResponse {

    private int status;
    private String Message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ErrorResponse(int status, String message) {
        this.status = status;
        Message = message;


    }
}
