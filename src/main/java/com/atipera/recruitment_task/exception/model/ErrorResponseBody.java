package com.atipera.recruitment_task.exception.model;

public class ErrorResponseBody {

    private String status;
    private String Message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ErrorResponseBody(String status, String message) {
        this.status = status;
        Message = message;


    }
}
