package com.atipera.recruitment_task.exception;

import com.atipera.recruitment_task.exception.model.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> handleUserNotFound(Exception exception) {
        String message = "The listed users and repositories cannot be searched either because the resources do not exist or you do not have permission to view them.";
        return new ResponseEntity<>(new ErrorResponseBody(exception.getMessage().toUpperCase(Locale.ROOT), message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HeaderValueException.class)
    public ResponseEntity<ErrorResponseBody> handle(Exception exception) {
        return new ResponseEntity<>(new ErrorResponseBody(HttpStatus.NOT_ACCEPTABLE.toString(),
                exception.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}
