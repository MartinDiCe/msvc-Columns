package com.diceprojects.msvccolumns.mapper;

/**
 * Interfaz para mapear desde un objeto de entrada a un objeto de salida.
 *
 * @param <I> Tipo del objeto de entrada.
 * @param <O> Tipo del objeto de salida.
 */
public interface IMapper<I, O> {

    /**
     * Realiza el mapeo de un objeto de entrada a un objeto de salida.
     *
     * @param in El objeto de entrada a mapear.
     * @return El objeto de salida mapeado.
     */
    O map(I in);
}
