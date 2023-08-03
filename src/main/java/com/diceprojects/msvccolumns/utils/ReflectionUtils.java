package com.diceprojects.msvccolumns.utils;

import java.lang.reflect.Field;

/**
 * Clase de utilidad para realizar operaciones de reflexión.
 */
public class ReflectionUtils {

    /**
     * Copia las propiedades no nulas de un objeto fuente a un objeto destino.
     *
     * @param source      Objeto fuente
     * @param destination Objeto destino
     */
    public static void copyNonNullProperties(Object source, Object destination) {
        try {
            Field[] sourceFields = source.getClass().getDeclaredFields();

            for (Field sourceField : sourceFields) {
                sourceField.setAccessible(true);

                Object sourceValue = sourceField.get(source);

                if (sourceValue != null) {
                    copyFieldToDestination(sourceField, sourceValue, destination);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error al copiar propiedades no nulas", e);
        }
    }

    private static void copyFieldToDestination(Field sourceField, Object sourceValue, Object destination) {
        try {
            Field destinationField = destination.getClass().getDeclaredField(sourceField.getName());
            destinationField.setAccessible(true);

            if (sourceField.getType().equals(String.class) && destinationField.getType().equals(Character.class)) {
                handleStringToCharacterConversion(sourceValue, destinationField, destination);
            } else {
                destinationField.set(destination, sourceValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error al copiar propiedades no nulas", e);
        }
    }

    private static void handleStringToCharacterConversion(Object sourceValue, Field destinationField, Object destination)
            throws IllegalAccessException {
        String sourceValueString = (String) sourceValue;
        if (sourceValueString.length() > 0) {
            destinationField.set(destination, sourceValueString.charAt(0));
        }
    }
}
