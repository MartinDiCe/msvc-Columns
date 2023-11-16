package com.diceprojects.msvccolumns.exceptions;

import java.util.List;

/**
 * Clase que representa una respuesta de error.
 */
public class ErrorResponse {

    /**
     * Mensaje general de error.
     */
    private String message;

    /**
     * Lista de mensajes de error detallados.
     */
    private List<ErrorMessage> errorMessages;

    /**
     * Constructor de la clase ErrorResponse.
     *
     * @param message       Mensaje general de error.
     * @param errorMessages Lista de mensajes de error detallados.
     */
    public ErrorResponse(String message, List<ErrorMessage> errorMessages) {
        this.message = message;
        this.errorMessages = errorMessages;
    }

    /**
     * Obtiene el mensaje general de error.
     *
     * @return Mensaje general de error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje general de error.
     *
     * @param message Mensaje general de error.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtiene la lista de mensajes de error detallados.
     *
     * @return Lista de mensajes de error detallados.
     */
    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Establece la lista de mensajes de error detallados.
     *
     * @param errorMessages Lista de mensajes de error detallados.
     */
    public void setErrorMessages(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
