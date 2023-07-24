package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.exceptions.ColumnsValidator;
import com.diceprojects.msvccolumns.mapper.FileColumnsDetailsInDTOColumns;
import com.diceprojects.msvccolumns.mapper.FileColumnsHeaderInDTOColumns;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import com.diceprojects.msvccolumns.persistences.repositories.FileColumnsDetailsRepository;
import com.diceprojects.msvccolumns.persistences.repositories.FileColumnsHeaderRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDateTime;;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileColumnsServiceImplement implements FileColumnsService {

    private final FileColumnsHeaderRepository headerRepository;
    private final FileColumnsHeaderInDTOColumns headerMapper;
    private final FileColumnsDetailsRepository detailsRepository;
    private final FileColumnsDetailsInDTOColumns detailsMapper;
    private final ColumnsValidator validator;

    public FileColumnsServiceImplement(FileColumnsHeaderRepository headerRepository, FileColumnsHeaderInDTOColumns mapperColumns, FileColumnsDetailsRepository detailsRepository, FileColumnsDetailsInDTOColumns detailsMapper, ColumnsValidator validator) {
        this.headerRepository = headerRepository;
        this.headerMapper = mapperColumns;
        this.detailsRepository = detailsRepository;
        this.detailsMapper = detailsMapper;
        this.validator = validator;
    }

    @Override
    public Optional<FileColumnsHeader> getConfigColumnFromFileName(String fileName) {
        try {
            List<FileColumnsHeader> allColumns = headerRepository.findAll();
            if (allColumns.isEmpty()) {
                return Optional
                        .empty();
            }

            List<FileColumnsHeader> matchingColumn = allColumns.stream()
                    .filter(column -> fileName.toLowerCase().startsWith(column.getStartFile().toLowerCase()))
                    .collect(Collectors.toList());

            if (matchingColumn.isEmpty()) {
                return Optional
                        .empty();
            } else if (matchingColumn.size() > 1) {
                throw new ResponseStatusException(HttpStatus
                        .BAD_REQUEST, "Se encontraron múltiples configuraciones de importación para el nombre de archivo.");
            }

            FileColumnsHeader fileColumnsHeader = matchingColumn.get(0);

            Optional<FileColumnsDetails> optionalFileColumnsDetails = detailsRepository
                    .findById(fileColumnsHeader.getId());

            if (optionalFileColumnsDetails.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(fileColumnsHeader);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .BAD_REQUEST, "El nombre de archivo no corresponde a ninguna configuración de importación. ", e);
        }
    }

    @Override
    public Optional<List<FileColumnsHeader>> findAll() {
        try {
            List<FileColumnsHeader> headers = headerRepository.findAll();

            if (headers.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(headers);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al obtener la lista de columnas. ", e);
        }
    }

    @Override
    public Optional<FileColumnsHeader> findByOperacionProcesoMapping(String operacion) {
        try {
            Optional<FileColumnsHeader> o =
                    headerRepository.findByOperacionProcesoMapping(operacion);

            if (o.isEmpty()) {
                return Optional.empty();
            }

            FileColumnsHeader fileColumnsHeader = o.get();

            return Optional.of(fileColumnsHeader);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al obtener la configuración de columnas para el archivo que intentan importar. ", e);
        }
    }

    @Override
    public Optional<FileColumnsHeader> createColumns(FileColumnsHeaderInDTO fileColumnsHeaderInDTO, FileColumnsDetailsInDTO fileColumnsDetailsInDTO) {
        try {
            FileColumnsHeader fileColumnsHeader = headerMapper.map(fileColumnsHeaderInDTO);
            FileColumnsDetails fileColumnsDetails = detailsMapper.map(fileColumnsDetailsInDTO);

            validator.validateColumns(fileColumnsHeader);

            fileColumnsHeader.setCreateDate(LocalDateTime.now());
            fileColumnsDetails.setCreateDate(LocalDateTime.now());

            saveColumns(fileColumnsHeader, fileColumnsDetails);

            return Optional.of(fileColumnsHeader);

        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<FileColumnsHeader> findById(Long id) {
        try {
            Optional<FileColumnsHeader> o = headerRepository.findById(id);
            if (o.isEmpty()) {
                return Optional.empty();
            }

            FileColumnsHeader fileColumnsHeader = o.get();

            return Optional.of(fileColumnsHeader);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al intentar buscar la configuración de las columnas con id: " + id, e);
        }
    }

    @Override
    public Optional<FileColumnsHeader> update(Long id, FileColumnsHeaderInDTO fileColumnsHeaderInDTO, FileColumnsDetailsInDTO fileColumnsDetailsInDTO) {
        try {
            Optional<FileColumnsHeader> optionalHeader = headerRepository.findById(id);
            if (optionalHeader.isEmpty()) {
                return Optional.empty();
            }

            FileColumnsHeader existingHeader = optionalHeader.get();
            FileColumnsDetails existingDetails = detailsRepository.findByFileColumnsHeader(existingHeader);

            LocalDateTime createDateDetail = existingDetails.getCreateDate();

            Field[] fields = fileColumnsHeaderInDTO.getClass().getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                Object fieldValue = field.get(fileColumnsHeaderInDTO);

                if (fieldValue != null) {
                    Field existingField = existingHeader.getClass().getDeclaredField(fieldName);
                    existingField.setAccessible(true);

                    if (field.getType().equals(String.class) && existingField.getType().equals(Character.class)) {
                        String fieldValueString = (String) fieldValue;
                        if (fieldValueString.length() > 0) {
                            existingField.set(existingHeader, fieldValueString.charAt(0));
                        }
                    } else {
                        existingField.set(existingHeader, fieldValue);
                    }
                }
            }

            Field[] fieldsDetails = fileColumnsDetailsInDTO.getClass().getDeclaredFields();

            for (Field field : fieldsDetails) {
                String fieldName = field.getName();
                Object fieldValue = field.get(fileColumnsDetailsInDTO);

                if (fieldValue != null) {
                    Field existingField = existingDetails.getClass().getDeclaredField(fieldName);
                    existingField.setAccessible(true);

                    if (field.getType().equals(String.class) && existingField.getType().equals(Character.class)) {
                        String fieldValueString = (String) fieldValue;
                        if (fieldValueString.length() > 0) {
                            existingField.set(existingDetails, fieldValueString.charAt(0));
                        }
                    } else {
                        existingField.set(existingDetails, fieldValue);
                    }
                }
            }

            existingHeader.setUpdateDate(LocalDateTime.now());
            existingDetails.setCreateDate(createDateDetail);
            existingDetails.setUpdateDate(LocalDateTime.now());

            saveColumns(existingHeader, existingDetails);

            return Optional.of(existingHeader);

        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<?> deleteFileColumns(Long id) {
        try {
            Optional<FileColumnsHeader> o = headerRepository.findById(id);

            if (o.isEmpty()) {
                return Optional.empty();
            }

            headerRepository.deleteById(id);

            return Optional.of("Register deleted successfully");
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT, "Error al intentar el registro con id: " + id, e);
        }
    }

    @Override
    public FileColumnsHeader saveColumns(FileColumnsHeader fileColumnsHeader, FileColumnsDetails fileColumnsDetails) {
        try {
            fileColumnsHeader.setFileColumnsDetails(fileColumnsDetails);
            fileColumnsDetails.setFileColumnsHeader(fileColumnsHeader);

            if (fileColumnsHeader.getId() != null && headerRepository.existsById(fileColumnsHeader.getId())) {
                return headerRepository.save(fileColumnsHeader);
            } else {
                return headerRepository.save(fileColumnsHeader);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
