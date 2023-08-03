package com.diceprojects.msvccolumns.exceptions;

import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import com.diceprojects.msvccolumns.persistences.repositories.FileColumnsHeaderRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase para validar la configuración de columnas.
 */
@Component
public class ColumnsValidator {

    private final FileColumnsHeaderRepository repository;

    /**
     * Constructor de la clase ColumnsValidator.
     *
     * @param repository Repositorio de FileColumnsHeader.
     */
    public ColumnsValidator(FileColumnsHeaderRepository repository) {
        this.repository = repository;
    }

    /**
     * Valida la configuración de columnas.
     *
     * @param fileColumnsHeader Configuración de columnas a validar.
     */
    public void validateColumns(FileColumnsHeader fileColumnsHeader) {
        List<String> errorFields = new ArrayList<>();

        validateStartFile(fileColumnsHeader.getStartFile(), errorFields);
        validateOperacionProceso(fileColumnsHeader.getOperacionProcesoMapping(), fileColumnsHeader.getTipoOperacionProcesoMapping(), errorFields);
        validateTipoEntidad(fileColumnsHeader.getOperacionProcesoMapping(), fileColumnsHeader.getTipoOperacionProcesoMapping(), fileColumnsHeader.getTipoEntidadMapping(), errorFields);
        validateStartFileMapping(fileColumnsHeader.getOperacionProcesoMapping(), fileColumnsHeader.getTipoOperacionProcesoMapping(), fileColumnsHeader.getTipoEntidadMapping(), fileColumnsHeader.getStartFile(), errorFields);

        checkForDuplicateFields(errorFields);
    }

    /**
     * Valida la configuración de columnas, excluyendo el ID.
     *
     * @param fileColumnsHeader Configuración de columnas a validar.
     */
    public void validateColumnsNotID(FileColumnsHeader fileColumnsHeader) {
        List<String> errorFields = new ArrayList<>();

        validateStartFileNotID(fileColumnsHeader.getStartFile(), fileColumnsHeader.getId(), errorFields);
        validateOperacionProcesoNotID(fileColumnsHeader.getOperacionProcesoMapping(), fileColumnsHeader.getTipoOperacionProcesoMapping(), fileColumnsHeader.getId(), errorFields);
        validateTipoEntidadNotID(fileColumnsHeader.getOperacionProcesoMapping(), fileColumnsHeader.getTipoOperacionProcesoMapping(), fileColumnsHeader.getTipoEntidadMapping(), fileColumnsHeader.getId(), errorFields);
        validateStartFileMappingNotID(fileColumnsHeader.getOperacionProcesoMapping(), fileColumnsHeader.getTipoOperacionProcesoMapping(), fileColumnsHeader.getTipoEntidadMapping(), fileColumnsHeader.getStartFile(), fileColumnsHeader.getId(), errorFields);

        checkForDuplicateFields(errorFields);
    }

    /**
     * Comprueba si hay campos duplicados y lanza una excepción si se encuentran.
     *
     * @param errorFields Lista de campos con errores.
     */
    private void checkForDuplicateFields(List<String> errorFields) {
        if (!errorFields.isEmpty()) {
            throw new RuntimeException("Campos repetidos encontrados: " + errorFields);
        }
    }

    /**
     * Valida la unicidad del campo startFile.
     *
     * @param startFile    Valor del campo startFile.
     * @param errorFields  Lista de campos con errores.
     */
    private void validateStartFile(String startFile, List<String> errorFields) {
        Optional<FileColumnsHeader> existingStartFile = repository.findByStartFile(startFile);
        if (existingStartFile.isPresent()) {
            errorFields.add("startFile");
        }
    }

    /**
     * Valida la unicidad del campo operacionProcesoMapping y tipoOperacionProcesoMapping.
     *
     * @param operacionProcesoMapping   Valor del campo operacionProcesoMapping.
     * @param tipoOperacionProcesoMapping Valor del campo tipoOperacionProcesoMapping.
     * @param errorFields              Lista de campos con errores.
     */
    private void validateOperacionProceso(String operacionProcesoMapping, String tipoOperacionProcesoMapping, List<String> errorFields) {
        Optional<FileColumnsHeader> existingOperacionProceso = repository.findByOperacionProcesoMapping(operacionProcesoMapping);
        if (existingOperacionProceso.isPresent()) {
            errorFields.add("operacionProcesoMapping");
        }

        Optional<FileColumnsHeader> existingTipoOperacionProceso = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMapping(
                operacionProcesoMapping, tipoOperacionProcesoMapping);
        if (existingTipoOperacionProceso.isPresent()) {
            errorFields.add("tipoOperacionProcesoMapping");
        }
    }

    /**
     * Valida la unicidad del campo tipoEntidadMapping.
     *
     * @param operacionProcesoMapping   Valor del campo operacionProcesoMapping.
     * @param tipoOperacionProcesoMapping Valor del campo tipoOperacionProcesoMapping.
     * @param tipoEntidadMapping      Valor del campo tipoEntidadMapping.
     * @param errorFields              Lista de campos con errores.
     */
    private void validateTipoEntidad(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, List<String> errorFields) {
        Optional<FileColumnsHeader> existingTipoEntidad = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMapping(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping);
        if (existingTipoEntidad.isPresent()) {
            errorFields.add("tipoEntidadMapping");
        }
    }

    /**
     * Valida la unicidad del campo startFile en relación con operacionProcesoMapping, tipoOperacionProcesoMapping y tipoEntidadMapping.
     *
     * @param operacionProcesoMapping   Valor del campo operacionProcesoMapping.
     * @param tipoOperacionProcesoMapping Valor del campo tipoOperacionProcesoMapping.
     * @param tipoEntidadMapping      Valor del campo tipoEntidadMapping.
     * @param startFile               Valor del campo startFile.
     * @param errorFields              Lista de campos con errores.
     */
    private void validateStartFileMapping(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile, List<String> errorFields) {
        Optional<FileColumnsHeader> existingStartFileMapping = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFile(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping, startFile);
        if (existingStartFileMapping.isPresent()) {
            errorFields.add("startFile");
        }
    }

    /**
     * Valida la unicidad del campo startFile excluyendo un ID específico.
     *
     * @param startFile    Valor del campo startFile.
     * @param id           Identificador a excluir.
     * @param errorFields  Lista de campos con errores.
     */
    private void validateStartFileNotID(String startFile, Long id, List<String> errorFields) {
        Optional<FileColumnsHeader> existingStartFile = repository.findByStartFileAndIdNot(startFile, id);
        if (existingStartFile.isPresent()) {
            errorFields.add("startFile");
        }
    }

    /**
     * Valida la unicidad del campo operacionProcesoMapping y tipoOperacionProcesoMapping excluyendo un ID específico.
     *
     * @param operacionProcesoMapping   Valor del campo operacionProcesoMapping.
     * @param tipoOperacionProcesoMapping Valor del campo tipoOperacionProcesoMapping.
     * @param id           Identificador a excluir.
     * @param errorFields  Lista de campos con errores.
     */
    private void validateOperacionProcesoNotID(String operacionProcesoMapping, String tipoOperacionProcesoMapping, Long id, List<String> errorFields) {
        Optional<FileColumnsHeader> existingOperacionProceso = repository.findByOperacionProcesoMappingAndIdNot(operacionProcesoMapping, id);
        if (existingOperacionProceso.isPresent()) {
            errorFields.add("operacionProcesoMapping");
        }

        Optional<FileColumnsHeader> existingTipoOperacionProceso = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndIdNot(
                operacionProcesoMapping, tipoOperacionProcesoMapping, id);
        if (existingTipoOperacionProceso.isPresent()) {
            errorFields.add("tipoOperacionProcesoMapping");
        }
    }

    /**
     * Valida la unicidad del campo tipoEntidadMapping excluyendo un ID específico.
     *
     * @param operacionProcesoMapping   Valor del campo operacionProcesoMapping.
     * @param tipoOperacionProcesoMapping Valor del campo tipoOperacionProcesoMapping.
     * @param tipoEntidadMapping      Valor del campo tipoEntidadMapping.
     * @param id           Identificador a excluir.
     * @param errorFields  Lista de campos con errores.
     */
    private void validateTipoEntidadNotID(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, Long id, List<String> errorFields) {
        Optional<FileColumnsHeader> existingTipoEntidad = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndIdNot(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping, id);
        if (existingTipoEntidad.isPresent()) {
            errorFields.add("tipoEntidadMapping");
        }
    }

    /**
     * Valida la unicidad del campo startFile en relación con operacionProcesoMapping, tipoOperacionProcesoMapping y tipoEntidadMapping excluyendo un ID específico.
     *
     * @param operacionProcesoMapping   Valor del campo operacionProcesoMapping.
     * @param tipoOperacionProcesoMapping Valor del campo tipoOperacionProcesoMapping.
     * @param tipoEntidadMapping      Valor del campo tipoEntidadMapping.
     * @param startFile               Valor del campo startFile.
     * @param id           Identificador a excluir.
     * @param errorFields  Lista de campos con errores.
     */
    private void validateStartFileMappingNotID(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile, Long id, List<String> errorFields) {
        Optional<FileColumnsHeader> existingStartFileMapping = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFileAndIdNot(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping, startFile, id);
        if (existingStartFileMapping.isPresent()) {
            errorFields.add("startFile");
        }
    }
}
