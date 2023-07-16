package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.exceptions.ColumnsValidator;
import com.diceprojects.msvccolumns.exceptions.ErrorMessage;
import com.diceprojects.msvccolumns.exceptions.ErrorResponse;
import com.diceprojects.msvccolumns.mapper.ColumnsInDTOColumns;
import com.diceprojects.msvccolumns.persistences.models.dto.ColumnsInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.Columns;
import com.diceprojects.msvccolumns.persistences.repositories.ColumnsRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColumnsServiceImplement implements ColumnsService {

    private final ColumnsRepository repository;
    private final ColumnsInDTOColumns mapper;
    private final ColumnsValidator validator;

    public ColumnsServiceImplement(ColumnsRepository repository, ColumnsInDTOColumns mapperColumns, ColumnsValidator validator) {
        this.repository = repository;
        this.mapper = mapperColumns;
        this.validator = validator;
    }

    @Override
    public Optional<Columns> getConfigColumnFromFileName(String fileName) {
        try {
            List<Columns> allColumns = repository.findAll();
            if (allColumns.isEmpty()) {
                return Optional
                        .empty();
            }

            Optional<Columns> matchingColumn = allColumns.stream()
                    .filter(column -> fileName.toLowerCase().startsWith(column.getStartFile().toLowerCase()))
                    .findFirst();

            return matchingColumn;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.
                    BAD_REQUEST, "El nombre de archivo no corresponde a ninguna configuración de importación. ", e);
        }
    }

    @Override
    public Optional<List<Columns>> findAll() {
        try {
            List<Columns> columns = repository.findAll();
            if (columns.isEmpty()) {
                return Optional
                        .empty();
            }

            return Optional
                    .of(columns);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al obtener la lista de columnas. ", e);
        }
    }

    @Override
    public Optional<Columns> findByOperacionProcesoMapping(String operacion) {
        try {
            Optional<Columns> o = repository.findByOperacionProcesoMapping(operacion);
            if (o.isEmpty()) {
                return Optional.empty();
            }

            Columns columns = o.get();
            return Optional.of(columns);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.
                    CONFLICT, "Error al obtener la configuración de columnas para el archivo que intentan importar. ", e);
        }
    }

    @Override
    public Optional<Columns> createColumns(ColumnsInDTO columnsInDTO) {
        try {
            Columns columns = mapper.map(columnsInDTO);

            validator.validateColumns(columns);

            columns.setCreateDate(LocalDateTime.now());

            saveColumns(columns);

            return Optional.of(columns);

        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Columns> findById(Long id) {
        try {
            Optional<Columns> o = repository.findById(id);
            if (o.isEmpty()) {
                return Optional.empty();
            }
            Columns columns = o.get();
            return Optional.of(columns);
        }

        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.
                    CONFLICT, "Error al intentar buscar la configuración de las columnas con id: . "+id, e);
        }

    }


    @Override
    public Optional<Columns> update(Long id, ColumnsInDTO columnsInDTO) {
        try {
            Optional<Columns> o = repository.findById(id);
            Columns columns = o.orElseThrow(() ->
                    new RuntimeException("No se encontró la entidad con el ID especificado"));

            Columns columnsMap = mapper.map(columnsInDTO);

            validator.validateColumnsNotID(columnsMap);

            BeanUtils.copyProperties(columnsMap, columns);
            columns.setUpdateDate(LocalDateTime.now());

            saveColumns(columns);

            return Optional.of(columns);

        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Columns saveColumns(Columns columns) {

        try {
            return repository.save(columns);
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());

        }
    }
}
