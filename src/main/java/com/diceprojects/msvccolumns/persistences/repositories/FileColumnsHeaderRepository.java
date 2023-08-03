package com.diceprojects.msvccolumns.persistences.repositories;

import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para acceder a los encabezados de las columnas de archivo en la base de datos.
 */
@Repository
public interface FileColumnsHeaderRepository extends JpaRepository<FileColumnsHeader, Long> {

    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo.
     *
     * @param operacion Operación de proceso de mapeo.
     * @return El encabezado de las columnas del archivo si existe.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMapping(String operacion);

    /**
     * Encuentra el encabezado de las columnas por el nombre de inicio de archivo.
     *
     * @param startFile Nombre de inicio de archivo.
     * @return El encabezado de las columnas del archivo si existe.
     */
    Optional<FileColumnsHeader> findByStartFile(String startFile);

    /**
     * Obtiene todos los encabezados de las columnas del archivo.
     *
     * @return Lista de todos los encabezados de las columnas del archivo.
     */
    List<FileColumnsHeader> findAll();

    /**
     * Encuentra el encabezado de las columnas por su ID.
     *
     * @param id ID del encabezado de las columnas.
     * @return El encabezado de las columnas del archivo si existe.
     */
    Optional<FileColumnsHeader> findById(Long id);
    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo y el tipo de operación de proceso de mapeo.
     *
     * @param operacionProcesoMapping       Operación de proceso de mapeo.
     * @param tipoOperacionProcesoMapping   Tipo de operación de proceso de mapeo.
     * @return El encabezado de las columnas del archivo si existe.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMapping(
            String operacionProcesoMapping, String tipoOperacionProcesoMapping);

    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo, el tipo de operación de proceso de mapeo y el tipo de entidad de mapeo.
     *
     * @param operacionProcesoMapping       Operación de proceso de mapeo.
     * @param tipoOperacionProcesoMapping   Tipo de operación de proceso de mapeo.
     * @param tipoEntidadMapping            Tipo de entidad de mapeo.
     * @return El encabezado de las columnas del archivo si existe.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMapping(
            String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping);

    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo, el tipo de operación de proceso de mapeo, el tipo de entidad de mapeo y el nombre de inicio de archivo.
     *
     * @param operacionProcesoMapping       Operación de proceso de mapeo.
     * @param tipoOperacionProcesoMapping   Tipo de operación de proceso de mapeo.
     * @param tipoEntidadMapping            Tipo de entidad de mapeo.
     * @param startFile                     Nombre de inicio de archivo.
     * @return El encabezado de las columnas del archivo si existe.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFile(
            String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile);

    /**
     * Encuentra el encabezado de las columnas por el nombre de inicio de archivo y excluye el encabezado con el ID proporcionado.
     *
     * @param startFile Nombre de inicio de archivo.
     * @param id        ID del encabezado de las columnas a excluir.
     * @return El encabezado de las columnas del archivo si existe y no tiene el ID especificado.
     */
    Optional<FileColumnsHeader> findByStartFileAndIdNot(String startFile, Long id);

    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo y excluye el encabezado con el ID proporcionado.
     *
     * @param operacionProcesoMapping Operación de proceso de mapeo.
     * @param id                      ID del encabezado de las columnas a excluir.
     * @return El encabezado de las columnas del archivo si existe y no tiene el ID especificado.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndIdNot(String operacionProcesoMapping, Long id);

    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo, el tipo de operación de proceso de mapeo y excluye el encabezado con el ID proporcionado.
     *
     * @param operacionProcesoMapping Operación de proceso de mapeo.
     * @param tipoOperacionProcesoMapping Tipo de operación de proceso de mapeo.
     * @param id                      ID del encabezado de las columnas a excluir.
     * @return El encabezado de las columnas del archivo si existe y no tiene el ID especificado.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndIdNot(
            String operacionProcesoMapping, String tipoOperacionProcesoMapping, Long id);

    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo, el tipo de operación de proceso de mapeo, el tipo de entidad de mapeo y excluye el encabezado con el ID proporcionado.
     *
     * @param operacionProcesoMapping Operación de proceso de mapeo.
     * @param tipoOperacionProcesoMapping Tipo de operación de proceso de mapeo.
     * @param tipoEntidadMapping      Tipo de entidad de mapeo.
     * @param id                      ID del encabezado de las columnas a excluir.
     * @return El encabezado de las columnas del archivo si existe y no tiene el ID especificado.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndIdNot(
            String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, Long id);

    /**
     * Encuentra el encabezado de las columnas por la operación de proceso de mapeo, el tipo de operación de proceso de mapeo, el tipo de entidad de mapeo, el nombre de inicio de archivo y excluye el encabezado con el ID proporcionado.
     *
     * @param operacionProcesoMapping Operación de proceso de mapeo.
     * @param tipoOperacionProcesoMapping Tipo de operación de proceso de mapeo.
     * @param tipoEntidadMapping      Tipo de entidad de mapeo.
     * @param startFile               Nombre de inicio de archivo.
     * @param id                      ID del encabezado de las columnas a excluir.
     * @return El encabezado de las columnas del archivo si existe y no tiene el ID especificado.
     */
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFileAndIdNot(
            String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile, Long id);

}
