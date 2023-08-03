package com.diceprojects.msvccolumns.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que maneja las excepciones globales para proporcionar respuestas coherentes.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción de validación lanzada por Spring cuando falla la validación de argumentos del método.
     *
     * @param ex Excepción de validación.
     * @return ResponseEntity con información sobre los campos de validación que han fallado.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<ErrorMessage> errorMessages = fieldErrors.stream()
                .map(fieldError ->
                        new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse =
                new ErrorResponse("Campos requeridos incompletos", errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    /**
     * Maneja excepciones de tiempo de ejecución no específicas proporcionando un mensaje de error general.
     *
     * @param ex Excepción de tiempo de ejecución.
     * @return ResponseEntity con información sobre la excepción de tiempo de ejecución.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        String errorMessage = ex.getMessage();

        ErrorResponse errorResponse;

        if (errorMessage != null) {
            errorResponse = new ErrorResponse("Error", Collections.singletonList(new ErrorMessage("RuntimeException", errorMessage)));
        } else {
            errorResponse = new ErrorResponse("Error", null);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
