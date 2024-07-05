package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.exceptions.ColumnsValidator;
import com.diceprojects.msvccolumns.mapper.FileColumnsDetailsInDTOColumns;
import com.diceprojects.msvccolumns.mapper.FileColumnsHeaderInDTOColumns;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsListDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderListDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import com.diceprojects.msvccolumns.persistences.repositories.FileColumnsDetailsRepository;
import com.diceprojects.msvccolumns.persistences.repositories.FileColumnsHeaderRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.diceprojects.msvccolumns.utils.ReflectionUtils;
import java.lang.reflect.Method;
import java.time.LocalDateTime;;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para la gestión de columnas de archivos.
 */
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

    /**
     * Obtiene la configuración de columna según el nombre del archivo.
     *
     * @param fileName Nombre del archivo.
     * @return La configuración de columna correspondiente, si existe.
     * @throws ResponseStatusException Si no se encuentra ninguna configuración o hay múltiples coincidencias.
     */
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

            FileColumnsDetails fileColumnsDetails = detailsRepository.findByFileColumnsHeader(fileColumnsHeader);

            if (fileColumnsDetails == null) {
                throw new ResponseStatusException(HttpStatus.
                        BAD_REQUEST, "No se encontró un detalle de columnas para el id del encabezado.");
            }

            return Optional.of(fileColumnsHeader);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus
                    .BAD_REQUEST, "El nombre de archivo no corresponde a ninguna configuración de importación. ", e);
        }
    }

    /**
     * Obtiene todas las configuraciones de columnas existentes.
     *
     * @return Lista de todas las configuraciones de columnas.
     * @throws ResponseStatusException Si hay un error al obtener la lista de columnas.
     */
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

    /**
     * Busca la configuración de columna por la operación de proceso.
     *
     * @param operacion Operación de proceso.
     * @return La configuración de columna correspondiente, si existe.
     * @throws ResponseStatusException Si hay un error al obtener la configuración de columnas.
     */
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

    /**
     * Crea nuevas columnas a partir de DTOs de encabezado y detalles.
     *
     * @param fileColumnsHeaderInDTO   DTO de encabezado.
     * @param fileColumnsDetailsInDTO DTO de detalles.
     * @return La configuración de columna recién creada.
     * @throws RuntimeException Si hay violaciones de restricciones o errores en tiempo de ejecución.
     */
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

    /**
     * Busca una configuración de columna por su ID.
     *
     * @param id ID de la configuración de columna.
     * @return La configuración de columna correspondiente, si existe.
     * @throws ResponseStatusException Si hay un error al buscar la configuración de columnas.
     */
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

    /**
     * Actualiza una configuración de columna existente.
     *
     * @param id                      ID de la configuración de columna a actualizar.
     * @param fileColumnsHeaderInDTO   DTO de encabezado actualizado.
     * @param fileColumnsDetailsInDTO DTO de detalles actualizado.
     * @return La configuración de columna actualizada.
     * @throws RuntimeException Si hay violaciones de restricciones o errores en tiempo de ejecución.
     */
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

            ReflectionUtils.copyNonNullProperties(fileColumnsHeaderInDTO, existingHeader);
            ReflectionUtils.copyNonNullProperties(fileColumnsDetailsInDTO, existingDetails);

            existingHeader.setUpdateDate(LocalDateTime.now());
            existingDetails.setCreateDate(createDateDetail);
            existingDetails.setUpdateDate(LocalDateTime.now());

            saveColumns(existingHeader, existingDetails);

            return Optional.of(existingHeader);

        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Elimina una configuración de columna por su ID.
     *
     * @param id ID de la configuración de columna a eliminar.
     * @return Mensaje de éxito después de la eliminación.
     * @throws ResponseStatusException Si hay un error al eliminar la configuración de columnas.
     */
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

    /**
     * Obtiene una lista de DTOs de encabezado de columnas.
     *
     * @return Lista de DTOs de encabezado de columnas.
     * @throws ResponseStatusException Si hay un error al obtener la lista de encabezados de columnas.
     */
    @Override
    public Optional <List<FileColumnsHeaderListDTO>> listColumnsHeader() {
        try {
            List<FileColumnsHeader> headers = headerRepository.findAll();

            if (headers.isEmpty()) {
                return Optional.empty();
            }

            List<FileColumnsHeaderListDTO> listHeaders = new ArrayList<>();

            for (FileColumnsHeader header : headers) {
                FileColumnsHeaderListDTO headerDTO = new FileColumnsHeaderListDTO();

                headerDTO.setId(header.getId());
                headerDTO.setStartFile(header.getStartFile());
                headerDTO.setTipoEntidadMapping(header.getTipoEntidadMapping());
                headerDTO.setTipoOperacionProcesoMapping(header.getTipoOperacionProcesoMapping());
                headerDTO.setOperacionProcesoMapping(header.getOperacionProcesoMapping());
                headerDTO.setDelimitadorArchivoMapping(header.getDelimitadorArchivoMapping());
                headerDTO.setCreateDate(header.getCreateDate());

                listHeaders.add(headerDTO);
            }

            return Optional.of(listHeaders);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al obtener la lista de columnas. ", e);
        }
    }

    /**
     * Obtiene una lista de DTOs de detalles de columnas.
     *
     * @return Lista de DTOs de detalles de columnas.
     * @throws ResponseStatusException Si hay un error al obtener la lista de detalles de columnas.
     */
    @Override
    public Optional<List<FileColumnsDetailsListDTO>> listColumnsDetail() {
        try {
            List<FileColumnsDetails> details = detailsRepository.findAll();

            if (details.isEmpty()) {
                return Optional.empty();
            }

            List<FileColumnsDetailsListDTO> listDetails = new ArrayList<>();

            for (FileColumnsDetails detail : details) {
                FileColumnsDetailsListDTO detailsDTO = new FileColumnsDetailsListDTO();

                detailsDTO.setId(detail.getId());
                detailsDTO.setCreateDate(detail.getCreateDate());

                for (int columnIndex = 0; columnIndex <= 40; columnIndex++) {
                    String columnMappingMethodName = "getColumn" + columnIndex + "Mapping";
                    String columnNameMethodName = "nameColumn" + columnIndex;

                    Method columnMappingMethod = FileColumnsDetails.class.getMethod(columnMappingMethodName);
                    Method columnNameMethod = FileColumnsDetails.class.getMethod(columnNameMethodName);

                    Integer columnMappingValue = (Integer) columnMappingMethod.invoke(detail);
                    String columnNameValue = (String) columnNameMethod.invoke(detail);

                    Method detailsDtoColumnMappingMethod = FileColumnsDetailsListDTO.class.getMethod("setColumn" + columnIndex + "Mapping", Integer.class);
                    Method detailsDtoColumnNameMethod = FileColumnsDetailsListDTO.class.getMethod("setNameColumn" + columnIndex, String.class);

                    detailsDtoColumnMappingMethod.invoke(detailsDTO, columnMappingValue);
                    detailsDtoColumnNameMethod.invoke(detailsDTO, columnNameValue);
                }

                listDetails.add(detailsDTO);
            }

            return Optional.of(listDetails);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al obtener la lista de columnas. ", e);
        }
    }

    /**
     * Guarda la configuración de columna y sus detalles asociados.
     *
     * @param fileColumnsHeader Configuración de columna.
     * @param fileColumnsDetails Detalles de columna.
     * @return La configuración de columna guardada.
     * @throws RuntimeException Si hay un error al guardar la configuración de columnas.
     */
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
