package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsListDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderListDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de columnas de archivos.
 */
public interface FileColumnsService {

    /**
     * Obtiene la configuración de columnas a partir del nombre del archivo.
     *
     * @param fileName Nombre del archivo.
     * @return Configuración de columnas opcional.
     */
    Optional<FileColumnsHeader> getConfigColumnFromFileName(String fileName);

    /**
     * Obtiene todas las configuraciones de columnas disponibles.
     *
     * @return Lista de configuraciones de columnas opcional.
     */
    Optional<List<FileColumnsHeader>> findAll();

    /**
     * Encuentra la configuración de columnas por mapeo de operación/proceso.
     *
     * @param operacion Mapeo de operación/proceso.
     * @return Configuración de columnas opcional.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMapping(String operacion);

    /**
     * Guarda la configuración de columnas y detalles en la base de datos.
     *
     * @param fileColumnsHeader Configuración de columnas.
     * @param fileColumnsDetails Detalles de columnas.
     * @return Configuración de columnas guardada.
     */
    FileColumnsHeader saveColumns(FileColumnsHeader fileColumnsHeader, FileColumnsDetails fileColumnsDetails);

    /**
     * Crea una nueva configuración de columnas a partir de DTOs.
     *
     * @param fileColumnsHeader DTO de configuración de columnas.
     * @param fileColumnsDetails DTO de detalles de columnas.
     * @return Configuración de columnas creada opcional.
     */
    Optional<FileColumnsHeader> createColumns(FileColumnsHeaderInDTO fileColumnsHeader, FileColumnsDetailsInDTO fileColumnsDetails);

    /**
     * Obtiene la configuración de columnas por su identificador.
     *
     * @param id Identificador de la configuración de columnas.
     * @return Configuración de columnas opcional.
     */
    Optional<FileColumnsHeader> findById(Long id);

    /**
     * Actualiza la configuración de columnas y detalles existentes.
     *
     * @param id Identificador de la configuración de columnas a actualizar.
     * @param fileColumnsHeaderInDTO DTO de configuración de columnas.
     * @param fileColumnsDetailsInDTO DTO de detalles de columnas.
     * @return Configuración de columnas actualizada opcional.
     */
    Optional<FileColumnsHeader> update(Long id, FileColumnsHeaderInDTO fileColumnsHeaderInDTO, FileColumnsDetailsInDTO fileColumnsDetailsInDTO);

    /**
     * Elimina la configuración de columnas por su identificador.
     *
     * @param id Identificador de la configuración de columnas a eliminar.
     * @return Respuesta opcional.
     */
    Optional<?> deleteFileColumns(Long id);

    /**
     * Obtiene una lista de DTOs de encabezado de columnas.
     *
     * @return Lista de DTOs de encabezado de columnas opcional.
     */
    Optional<List<FileColumnsHeaderListDTO>> listColumnsHeader();

    /**
     * Obtiene una lista de DTOs de detalles de columnas.
     *
     * @return Lista de DTOs de detalles de columnas opcional.
     */
    Optional<List<FileColumnsDetailsListDTO>> listColumnsDetail();
}
