package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.mapper.ColumnsInDTOColumns;
import com.diceprojects.msvccolumns.persistences.models.dto.ColumnsInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.Columns;
import com.diceprojects.msvccolumns.persistences.repositories.ColumnsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ColumnsServiceImplement implements ColumnsService {

    private final ColumnsRepository repository;
    private final ColumnsInDTOColumns mapper;
    public ColumnsServiceImplement(ColumnsRepository repository, ColumnsInDTOColumns mapperColumns) {
        this.repository = repository;
        this.mapper = mapperColumns;
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
        }

        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.
                    CONFLICT, "Error al obtener la configuración de columnas para el archivo que intentan importar. ", e);
        }

    }

    @Override
    public Optional<Columns> createColumns(ColumnsInDTO columnsInDTO) {

        String operacionProcesoMapping = columnsInDTO.getOperacionProcesoMapping();
        String tipoOperacionProcesoMapping = columnsInDTO.getTipoOperacionProcesoMapping();
        String tipoEntidadMapping = columnsInDTO.getTipoEntidadMapping();
        String startFile = columnsInDTO.getStartFile();

        Optional<Columns> oExistingStartFile = repository.findByStartFile(startFile);
        if (oExistingStartFile.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El File Start ya existe en otra configuración de columnas");
        }

        Optional<Columns> oProcesoMapping = repository.findByOperacionProcesoMapping(operacionProcesoMapping);
        if (oProcesoMapping.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La operación ya tiene una configuración de columnas");
        }

       Optional<Columns> oTipoProcesoMapping = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMapping(
                operacionProcesoMapping, tipoOperacionProcesoMapping);
        if (oTipoProcesoMapping.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El tipo de operación ya tiene una configuración de columnas");
        }

        Optional<Columns> oTipoEntidadMapping = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMapping(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping);
        if (oTipoEntidadMapping.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El tipo de entidad ya tiene una configuración de columnas");
        }

        Optional<Columns> oStartFileMapping = repository.findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFile(
                operacionProcesoMapping, tipoOperacionProcesoMapping, tipoEntidadMapping, startFile);
        if (oStartFileMapping.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La nomenclatura para el archivo ya existe para la misma operación");
        }

        Columns columns = mapper.map(columnsInDTO);

        saveColumns(columns);

        return Optional.of(columns);
    }

    @Override
    public Columns saveColumns(Columns columns) {

        try {
            return repository.save(columns);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.
                    CONFLICT, "Error al guardar columnas. ", e);

        }

    }

}
