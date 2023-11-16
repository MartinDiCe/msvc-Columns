package com.diceprojects.msvccolumns.persistences.repositories;

import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para acceder a los detalles de las columnas del archivo en la base de datos.
 */
@Repository
public interface FileColumnsDetailsRepository extends JpaRepository<FileColumnsDetails, Long> {

        /**
         * Encuentra los detalles de las columnas por el ID del encabezado de las columnas del archivo.
         *
         * @param fileColumnsHeaderId ID del encabezado de las columnas del archivo.
         * @return Los detalles de las columnas del archivo si existen.
         */
        Optional<FileColumnsDetails> findByFileColumnsHeaderId(Long fileColumnsHeaderId);

        /**
         * Obtiene todos los detalles de las columnas del archivo.
         *
         * @return Lista de todos los detalles de las columnas del archivo.
         */
        List<FileColumnsDetails> findAll();

        /**
         * Encuentra los detalles de las columnas por su ID.
         *
         * @param id ID de los detalles de las columnas.
         * @return Los detalles de las columnas del archivo si existen.
         */
        Optional<FileColumnsDetails> findById(Long id);

        /**
         * Encuentra los detalles de las columnas por el encabezado de las columnas del archivo.
         *
         * @param fileColumnsHeader Encabezado de las columnas del archivo.
         * @return Los detalles de las columnas del archivo si existen.
         */
        FileColumnsDetails findByFileColumnsHeader(FileColumnsHeader fileColumnsHeader);
}
