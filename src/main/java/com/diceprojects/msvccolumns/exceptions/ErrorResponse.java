package com.diceprojects.msvccolumns.exceptions;

import java.util.List;

public class ErrorResponse {
    private String message;
    private List<ErrorMessage> errorMessages;

    public ErrorResponse(String message, List<ErrorMessage> errorMessages) {
        this.message = message;
        this.errorMessages = errorMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public List<ErrorMessage> getErrorMessages() {

        return errorMessages;
    }

    public void setErrorMessages(List<ErrorMessage> errorMessages) {

        this.errorMessages = errorMessages;
    }
}