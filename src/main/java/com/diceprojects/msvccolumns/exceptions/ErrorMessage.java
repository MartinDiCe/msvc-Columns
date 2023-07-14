package com.diceprojects.msvccolumns.exceptions;

import lombok.Data;

import java.util.Objects;

@Data
public class ErrorMessage {

    private String field;
    private String message;

    public ErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}