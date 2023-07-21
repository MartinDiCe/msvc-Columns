package com.diceprojects.msvccolumns.controllers;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsRequestInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import com.diceprojects.msvccolumns.services.FileColumnsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/msvc-columns")
public class ColumnsController {

    private final FileColumnsService fileColumnsService;

    public ColumnsController(FileColumnsService fileColumnsService) {

        this.fileColumnsService = fileColumnsService;

    }

    @GetMapping("/get-config")
    public ResponseEntity<?> getConfigColumnFromFileName(@RequestParam("fileName") String fileName) {

        Optional<FileColumnsDetails> o =
                fileColumnsService.getConfigColumnFromFileName(fileName);

        if (o.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró configuración para el archivo que intenta importar. File Name: "+fileName);
        }

        FileColumnsDetails fileColumnsDetails = o.get();

        return ResponseEntity
                .ok()
                .body(fileColumnsDetails);
    }


    @GetMapping("/list")
    public ResponseEntity<List<FileColumnsHeader>> listAllColumns() {
        Optional<List<FileColumnsHeader>> o = fileColumnsService.findAll();

        if (o.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }

        List<FileColumnsHeader> fileColumnsHeaders = o.get();

        return ResponseEntity
                .ok()
                .body(fileColumnsHeaders);
    }

    @GetMapping("/find-by-operacion")
    public ResponseEntity<?> findByOperacionProcesoMapping(@RequestParam("operacion") String operacion) {

        Optional<FileColumnsHeader> o =
                fileColumnsService.findByOperacionProcesoMapping(operacion);

        if (o.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró configuración para la operación que intenta procesar. Operación: "+operacion);
        }

        FileColumnsHeader fileColumnsHeader = o.get();

        return ResponseEntity
                .ok()
                .body(fileColumnsHeader);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createColumns(@Valid @RequestBody FileColumnsRequestInDTO fileColumnsRequestInDTO) {

            Optional<FileColumnsHeader> o =
                    fileColumnsService.createColumns
                            (fileColumnsRequestInDTO.getFileColumnsHeaderInDTO(),
                                    fileColumnsRequestInDTO.getFileColumnsDetailsInDTO());

        if (o.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se puede crear el nuevo registro de configuración .");
        }

        FileColumnsHeader fileColumnsHeader = o.get();

        return ResponseEntity
                .status(HttpStatus
                        .CREATED).body(fileColumnsHeader);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getColumnById(@PathVariable Long id) {

            Optional<FileColumnsHeader> o = fileColumnsService.findById(id);

            if (o.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró información para el identificador enviado. ID: "+ id);
            }

            FileColumnsHeader fileColumnsHeader = o.get();

            return ResponseEntity
                    .ok()
                    .body(fileColumnsHeader);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<FileColumnsHeader> o = fileColumnsService.findById(id);
        if (o.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró información para el identificador enviado. ID: "+ id);
        }
        fileColumnsService.deleteFileColumns(o.get().getId());
        return ResponseEntity
                .ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FileColumnsRequestInDTO fileColumnsRequestInDTO) {

        Optional<FileColumnsHeader> o =
                fileColumnsService
                        .update(id,
                                fileColumnsRequestInDTO.getFileColumnsHeaderInDTO(),
                                fileColumnsRequestInDTO.getFileColumnsDetailsInDTO());

        if (o.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar o no existe el registro. ID: "+ id);
        }

        FileColumnsHeader fileColumnsHeader =
                o.get();

        return ResponseEntity
                .ok(fileColumnsHeader);
    }

}
