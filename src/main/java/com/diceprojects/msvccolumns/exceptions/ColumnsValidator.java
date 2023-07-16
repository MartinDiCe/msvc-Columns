package com.diceprojects.msvccolumns.exceptions;

import com.diceprojects.msvccolumns.persistences.models.entities.Columns;
import com.diceprojects.msvccolumns.persistences.repositories.ColumnsRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ColumnsValidator {
    private final ColumnsRepository repository;

    public ColumnsValidator(ColumnsRepository repository) {
        this.repository = repository;
    }

    public void validateColumns(Columns columns) {
        List<String> errorFields = new ArrayList<>();

        validateStartFile(columns.getStartFile(), errorFields);
        validateOperacionProceso(columns.getOperacionProcesoMapping(), columns.getTipoOperacionProcesoMapping(), errorFields);
        validateTipoEntidad(columns.getOperacionProcesoMapping(), columns.getTipoOperacionProcesoMapping(), columns.getTipoEntidadMapping(), errorFields);
        validateStartFileMapping(columns.getOperacionProcesoMapping(), columns.getTipoOperacionProcesoMapping(), columns.getTipoEntidadMapping(), columns.getStartFile(), errorFields);

        checkForDuplicateFields(errorFields);
    }

    public void validateColumnsNotID(Columns columns) {
        List<String> errorFields = new ArrayList<>();

        validateStartFileNotID(columns.getStartFile(), columns.getId(), errorFields);
        validateOperacionProcesoNotID(columns.getOperacionProcesoMapping(), columns.getTipoOperacionProcesoMapping(), columns.getId(), errorFields);
        validateTipoEntidadNotID(columns.getOperacionProcesoMapping(), columns.getTipoOperacionProcesoMapping(), columns.getTipoEntidadMapping(), columns.getId(), errorFields);
        validateStartFileMappingNotID(columns.getOperacionProcesoMapping(), columns.getTipoOperacionProcesoMapping(), columns.getTipoEntidadMapping(), columns.getStartFile(), columns.getId(), errorFields);

        checkForDuplicateFields(errorFields);
    }

    private void checkForDuplicateFields(List<String> errorFields) {
        if (!errorFields.isEmpty()) {
            throw new RuntimeException("Campos repetidos encontrados: " + errorFields);
        }
    }

    private void validateStartFile(String startFile, List<String> errorFields) {
        Optional<Columns> existingStartFile = repository.findByStartFile(startFile);
        if (existingStartFile.isPresent()) {
            errorFields.add("startFile");
        }
    }

    private void validateOperacionProceso(String operacionProcesoMapping, String tipoOperacionProcesoMapping, List<String> errorFields) {
        Optional<Columns> existingOperacionProceso = repository.findByOperacionProcesoMapping(operacionProcesoMapping);
        if (existingOperacionProceso.isPresent()) {
            errorFields.add("operacionProcesoMapping");
        }

        Optional<Columns> existingTipoOperacionProceso = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMapping(
                operacionProcesoMapping, tipoOperacionProcesoMapping);
        if (existingTipoOperacionProceso.isPresent()) {
            errorFields.add("tipoOperacionProcesoMapping");
        }
    }

    private void validateTipoEntidad(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, List<String> errorFields) {
        Optional<Columns> existingTipoEntidad = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMapping(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping);
        if (existingTipoEntidad.isPresent()) {
            errorFields.add("tipoEntidadMapping");
        }
    }

    private void validateStartFileMapping(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile, List<String> errorFields) {
        Optional<Columns> existingStartFileMapping = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFile(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping, startFile);
        if (existingStartFileMapping.isPresent()) {
            errorFields.add("startFile");
        }
    }

    private void validateStartFileNotID(String startFile, Long id, List<String> errorFields) {
        Optional<Columns> existingStartFile = repository.findByStartFileAndIdNot(startFile, id);
        if (existingStartFile.isPresent()) {
            errorFields.add("startFile");
        }
    }

    private void validateOperacionProcesoNotID(String operacionProcesoMapping, String tipoOperacionProcesoMapping, Long id, List<String> errorFields) {
        Optional<Columns> existingOperacionProceso = repository.findByOperacionProcesoMappingAndIdNot(operacionProcesoMapping, id);
        if (existingOperacionProceso.isPresent()) {
            errorFields.add("operacionProcesoMapping");
        }

        Optional<Columns> existingTipoOperacionProceso = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndIdNot(
                operacionProcesoMapping, tipoOperacionProcesoMapping, id);
        if (existingTipoOperacionProceso.isPresent()) {
            errorFields.add("tipoOperacionProcesoMapping");
        }
    }

    private void validateTipoEntidadNotID(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, Long id, List<String> errorFields) {
        Optional<Columns> existingTipoEntidad = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndIdNot(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping, id);
        if (existingTipoEntidad.isPresent()) {
            errorFields.add("tipoEntidadMapping");
        }
    }

    private void validateStartFileMappingNotID(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile, Long id, List<String> errorFields) {
        Optional<Columns> existingStartFileMapping = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFileAndIdNot(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping, startFile, id);
        if (existingStartFileMapping.isPresent()) {
            errorFields.add("startFile");
        }
    }
}
