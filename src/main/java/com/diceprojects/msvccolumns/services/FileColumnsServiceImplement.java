package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.exceptions.ColumnsValidator;
import com.diceprojects.msvccolumns.mapper.FileColumnsDetailsInDTOColumns;
import com.diceprojects.msvccolumns.mapper.FileColumnsHeaderInDTOColumns;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import com.diceprojects.msvccolumns.persistences.repositories.FileColumnsDetailsRepository;
import com.diceprojects.msvccolumns.persistences.repositories.FileColumnsHeaderRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColumnsServiceImplement implements ColumnsService {

    private final FileColumnsHeaderRepository repository;
    private final FileColumnsHeaderInDTOColumns headerMapper;
    private final FileColumnsDetailsRepository detailsRepository;
    private final FileColumnsDetailsInDTOColumns detailsMapper;
    private final ColumnsValidator validator;

    public ColumnsServiceImplement(FileColumnsHeaderRepository repository, FileColumnsHeaderInDTOColumns mapperColumns, FileColumnsDetailsRepository detailsRepository, FileColumnsDetailsInDTOColumns detailsMapper, ColumnsValidator validator) {
        this.repository = repository;
        this.headerMapper = mapperColumns;
        this.detailsRepository = detailsRepository;
        this.detailsMapper = detailsMapper;
        this.validator = validator;
    }

    @Override
    public Optional<FileColumnsHeader> getConfigColumnFromFileName(String fileName) {
        try {
            List<FileColumnsHeader> allColumns = repository.findAll();
            if (allColumns.isEmpty()) {
                return Optional
                        .empty();
            }

            Optional<FileColumnsHeader> matchingColumnsHeader = allColumns.stream()
                    .filter(columnsHeader -> fileName.toLowerCase()
                            .startsWith(columnsHeader
                                    .getStartFile().toLowerCase()))
                    .findFirst();

            return matchingColumnsHeader;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.
                    BAD_REQUEST, "El nombre de archivo no corresponde a ninguna configuración de importación. ", e);
        }
    }

    @Override
    public Optional<List<FileColumnsDTO>> findAll() {
        try {
            List<FileColumnsHeader> headers = repository.findAll();

            if (headers.isEmpty()) {
                return Optional.empty();
            }

            List<FileColumnsDTO> fileColumnsDTOs = new ArrayList<>();

            for (FileColumnsHeader fileColumnsHeader : headers) {
                Optional<FileColumnsDetails> details = detailsRepository.findByFileColumnsHeaderId(fileColumnsHeader.getId());
                if (details.isPresent()) {
                    FileColumnsDTO fileColumnsDTO = new FileColumnsDTO();
                    fileColumnsDTO.setFileColumnsHeader(fileColumnsHeader);
                    fileColumnsDTO.setFileColumnsDetails(details.get());
                    fileColumnsDTOs.add(fileColumnsDTO);
                }
            }

            return Optional.of(fileColumnsDTOs);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al obtener la lista de columnas. ", e);
        }
    }

    @Override
    public Optional<FileColumnsDTO> findByOperacionProcesoMapping(String operacion) {
        try {
            Optional<FileColumnsHeader> o =
                    repository.findByOperacionProcesoMapping(operacion);

            if (o.isEmpty()) {
                return Optional.empty();
            }

            FileColumnsHeader fileColumnsHeader = o.get();

            Optional<FileColumnsDetails> details =
                    detailsRepository.findByFileColumnsHeaderId(fileColumnsHeader.getId());

            if (details.isEmpty()) {
                return Optional.empty();
            }

            FileColumnsDTO fileColumnsDTO = new FileColumnsDTO();
            fileColumnsDTO.setFileColumnsHeader(fileColumnsHeader);
            fileColumnsDTO.setFileColumnsDetails(details.get());

            return Optional.of(fileColumnsDTO);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al obtener la configuración de columnas para el archivo que intentan importar. ", e);
        }
    }

    @Override
    public Optional<FileColumnsDTO> createColumns(FileColumnsHeaderInDTO fileColumnsHeaderInDTO, FileColumnsDetailsInDTO fileColumnsDetailsInDTO) {
        try {
            FileColumnsHeader fileColumnsHeader = headerMapper.map(fileColumnsHeaderInDTO);
            FileColumnsDetails fileColumnsDetails = detailsMapper.map(fileColumnsDetailsInDTO);

            validator.validateColumns(fileColumnsHeader);

            fileColumnsHeader.setCreateDate(LocalDateTime.now());

            saveColumns(fileColumnsHeader, fileColumnsDetails);

            FileColumnsDTO fileColumnsDTO = new FileColumnsDTO();
            fileColumnsDTO.setFileColumnsHeader(fileColumnsHeader);
            fileColumnsDTO.setFileColumnsDetails(fileColumnsDetails);

            return Optional.of(fileColumnsDTO);
        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Optional<FileColumnsDTO> findById(Long id) {
        try {
            Optional<FileColumnsHeader> o = repository.findById(id);
            if (o.isEmpty()) {
                return Optional.empty();
            }

            FileColumnsHeader fileColumnsHeader = o.get();

            Optional<FileColumnsDetails> details = detailsRepository.findByFileColumnsHeader(fileColumnsHeader);
            if (details.isEmpty()) {
                return Optional.empty();
            }

            FileColumnsDTO fileColumnsDTO = new FileColumnsDTO();
            fileColumnsDTO.setFileColumnsHeader(fileColumnsHeader);
            fileColumnsDTO.setFileColumnsDetails(details.get());

            return Optional.of(fileColumnsDTO);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al intentar buscar la configuración de las columnas con id: " + id, e);
        }
    }

    @Override
    public Optional<FileColumnsHeader> update(Long id, FileColumnsHeaderInDTO fileColumnsHeaderInDTO) {
        try {
            Optional<FileColumnsHeader> o = repository.findById(id);
            FileColumnsHeader fileColumnsHeader = o.orElseThrow(() ->
                    new RuntimeException("No se encontró la entidad con el ID especificado"));

            FileColumnsHeader fileColumnsHeaderMap = headerMapper.map(fileColumnsHeaderInDTO);

            validator.validateColumnsNotID(fileColumnsHeaderMap);

            BeanUtils.copyProperties(fileColumnsHeaderMap, fileColumnsHeader);
            fileColumnsHeader.setUpdateDate(LocalDateTime.now());

            saveColumns(fileColumnsHeader);

            return Optional.of(fileColumnsHeader);

        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public FileColumnsHeader saveColumns(FileColumnsHeader fileColumnsHeader, FileColumnsDetails fileColumnsDetails) {
        try {
            fileColumnsHeader.setFileColumnsDetails(fileColumnsDetails);
            fileColumnsDetails.setFileColumnsHeader(fileColumnsHeader);
            fileColumnsHeader.setCreateDate(LocalDateTime.now());
            fileColumnsDetails.setCreateDate(LocalDateTime.now());

            return repository.save(fileColumnsHeader);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
