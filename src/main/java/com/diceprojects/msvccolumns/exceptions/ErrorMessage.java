package com.diceprojects.msvccolumns.exceptions;

import lombok.Data;

import java.util.Objects;

/**
 * Clase que representa un mensaje de error.
 */
@Data
public class ErrorMessage {

    /**
     * Nombre del atributo relacionado con el error.
     */
    private String attributeName;

    /**
     * Descripción del error.
     */
    private String errorDescription;

    /**
     * Constructor de la clase ErrorMessage.
     *
     * @param attributeName    Nombre del atributo relacionado con el error.
     * @param errorDescription Descripción del error.
     */
    public ErrorMessage(String attributeName, String errorDescription) {
        this.attributeName = attributeName;
        this.errorDescription = errorDescription;
    }

    /**
     * Compara si dos objetos ErrorMessage son iguales, basándose en el nombre del atributo.
     *
     * @param o Objeto a comparar.
     * @return `true` si los objetos son iguales, `false` en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(attributeName, that.attributeName);
    }

    /**
     * Genera el código hash para el objeto ErrorMessage, basándose en el nombre del atributo.
     *
     * @return Código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(attributeName);
    }

    /**
     * Genera una representación en cadena del objeto ErrorMessage.
     *
     * @return Representación en cadena del objeto.
     */
    @Override
    public String toString() {
        return "ErrorMessage{" +
                "field='" + attributeName + '\'' +
                ", message='" + errorDescription + '\'' +
                '}';
    }
}
