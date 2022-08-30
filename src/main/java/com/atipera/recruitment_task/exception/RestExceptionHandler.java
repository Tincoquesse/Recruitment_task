package com.atipera.recruitment_task.exception;

import com.atipera.recruitment_task.exception.model.ErrorResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> handleUserNotFound(Exception exception) {
        String message = "The listed users and repositories cannot be searched either " +
                "because the resources do not exist or you do not have permission to view them.";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ErrorResponseBody errorResponseBody = new ErrorResponseBody("404", message);
        return new ResponseEntity<>(errorResponseBody, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<byte[]> handleMethodNotSupported(final HttpMediaTypeNotAcceptableException e) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Object response = new ErrorResponseBody("406", e.getMessage());
        return new ResponseEntity<>(objectMapper.writeValueAsBytes(response), headers, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponseBody> handleLimitExceed(final HttpClientErrorException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ErrorResponseBody response = new ErrorResponseBody("403", e.getMessage());
        return new ResponseEntity<>(response, headers, HttpStatus.FORBIDDEN);
    }


}
