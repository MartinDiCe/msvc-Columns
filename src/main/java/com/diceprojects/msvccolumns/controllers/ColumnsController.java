package com.diceprojects.msvccolumns.controllers;

import com.diceprojects.msvccolumns.persistences.models.dto.*;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import com.diceprojects.msvccolumns.services.FileColumnsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para la gestión de configuraciones de columnas de archivos.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/msvc-columns")
public class ColumnsController {

    private final FileColumnsService fileColumnsService;

    public ColumnsController(FileColumnsService fileColumnsService) {
        this.fileColumnsService = fileColumnsService;
    }

    /**
     * Obtiene la configuración de columnas a partir del nombre del archivo.
     *
     * @param fileName Nombre del archivo.
     * @return ResponseEntity con la configuración de columnas o un mensaje de error.
     */
    @GetMapping("/get-config")
    public ResponseEntity<?> getConfigColumnFromFileName(@RequestParam("fileName") String fileName) {
        Optional<FileColumnsHeader> o = fileColumnsService.getConfigColumnFromFileName(fileName);

        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró configuración para el archivo que intenta importar. File Name: " + fileName);
        }

        FileColumnsHeader fileColumnsHeader = o.get();

        return ResponseEntity.ok().body(fileColumnsHeader);
    }

    /**
     * Lista todas las configuraciones de columnas.
     *
     * @return ResponseEntity con la lista de configuraciones o un mensaje de no contenido.
     */
    @GetMapping("/list")
    public ResponseEntity<List<FileColumnsHeader>> listAllColumns() {
        Optional<List<FileColumnsHeader>> o = fileColumnsService.findAll();

        if (o.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FileColumnsHeader> fileColumnsHeaders = o.get();

        return ResponseEntity.ok().body(fileColumnsHeaders);
    }

    /**
     * Lista las configuraciones de columnas en formato resumido.
     *
     * @return ResponseEntity con la lista de configuraciones resumidas o un mensaje de no contenido.
     */
    @GetMapping("/listHeader")
    public ResponseEntity<List<FileColumnsHeaderListDTO>> listAllHeaders() {
        Optional<List<FileColumnsHeaderListDTO>> o = fileColumnsService.listColumnsHeader();

        if (o.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FileColumnsHeaderListDTO> columnsHeaders = o.get();

        return ResponseEntity.ok().body(columnsHeaders);
    }

    /**
     * Lista los detalles de todas las configuraciones de columnas.
     *
     * @return ResponseEntity con la lista de detalles de configuraciones o un mensaje de no contenido.
     */
    @GetMapping("/listDetails")
    public ResponseEntity<List<FileColumnsDetailsListDTO>> listAllDetails() {
        Optional<List<FileColumnsDetailsListDTO>> o = fileColumnsService.listColumnsDetail();

        if (o.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FileColumnsDetailsListDTO> columnsDetails = o.get();

        return ResponseEntity.ok().body(columnsDetails);
    }

    /**
     * Busca una configuración de columnas por operación de proceso.
     *
     * @param operacion Operación de proceso.
     * @return ResponseEntity con la configuración de columnas encontrada o un mensaje de error.
     */
    @GetMapping("/find-by-operacion")
    public ResponseEntity<?> findByOperacionProcesoMapping(@RequestParam("operacion") String operacion) {
        Optional<FileColumnsHeader> o = fileColumnsService.findByOperacionProcesoMapping(operacion);

        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró configuración para la operación que intenta procesar. Operación: " + operacion);
        }

        FileColumnsHeader fileColumnsHeader = o.get();

        return ResponseEntity.ok().body(fileColumnsHeader);
    }

    /**
     * Crea una nueva configuración de columnas.
     *
     * @param fileColumnsRequestInDTO Datos de entrada para la creación.
     * @return ResponseEntity con la configuración creada o un mensaje de error.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createColumns(@Valid @RequestBody FileColumnsRequestInDTO fileColumnsRequestInDTO) {
        Optional<FileColumnsHeader> o = fileColumnsService.createColumns(
                fileColumnsRequestInDTO.getFileColumnsHeaderInDTO(),
                fileColumnsRequestInDTO.getFileColumnsDetailsInDTO());

        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede crear el nuevo registro de configuración.");
        }

        FileColumnsHeader fileColumnsHeader = o.get();

        return ResponseEntity.status(HttpStatus.CREATED).body(fileColumnsHeader);
    }

    /**
     * Obtiene la configuración de columnas por identificador.
     *
     * @param id Identificador de la configuración.
     * @return ResponseEntity con la configuración encontrada o un mensaje de error.
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getColumnById(@PathVariable Long id) {
        Optional<FileColumnsHeader> o = fileColumnsService.findById(id);

        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró información para el identificador enviado. ID: " + id);
        }

        FileColumnsHeader fileColumnsHeader = o.get();

        return ResponseEntity.ok().body(fileColumnsHeader);
    }

    /**
     * Elimina una configuración de columnas por identificador.
     *
     * @param id Identificador de la configuración a eliminar.
     * @return ResponseEntity con un mensaje de éxito o un mensaje de error.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<FileColumnsHeader> o = fileColumnsService.findById(id);

        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró información para el identificador enviado. ID: " + id);
        }

        fileColumnsService.deleteFileColumns(o.get().getId());
        return ResponseEntity.ok().build();
    }

    /**
     * Actualiza la configuración de columnas por identificador.
     *
     * @param id                    Identificador de la configuración a actualizar.
     * @param fileColumnsRequestInDTO Datos de entrada para la actualización.
     * @return ResponseEntity con la configuración actualizada o un mensaje de error.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid FileColumnsRequestInDTO fileColumnsRequestInDTO) {
        Optional<FileColumnsHeader> o = fileColumnsService.update(id,
                fileColumnsRequestInDTO.getFileColumnsHeaderInDTO(),
                fileColumnsRequestInDTO.getFileColumnsDetailsInDTO());

        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar o no existe el registro. ID: " + id);
        }

        FileColumnsHeader fileColumnsHeader = o.get();

        return ResponseEntity.ok(fileColumnsHeader);
    }
}
