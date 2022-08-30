package com.atipera.recruitment_task.exception.model;

public class ErrorResponseBody {

    private String status;
    private String Message;


    public ErrorResponseBody(String status, String message) {
        this.status = status;
        Message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return Message;
    }
}
