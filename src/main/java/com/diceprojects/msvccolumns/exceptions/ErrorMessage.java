package com.diceprojects.msvccolumns.exceptions;

import lombok.Data;

import java.util.Objects;

@Data
public class ErrorMessage {
    private String attributeName;
    private String errorDescription;

    public ErrorMessage(String attributeName, String errorDescription) {
        this.attributeName = attributeName;
        this.errorDescription = errorDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(attributeName, that.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName);
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "field='" + attributeName + '\'' +
                ", message='" + errorDescription + '\'' +
                '}';
    }
}