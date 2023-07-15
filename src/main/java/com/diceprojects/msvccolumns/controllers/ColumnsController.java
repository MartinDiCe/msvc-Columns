package com.diceprojects.msvccolumns.controllers;

import com.diceprojects.msvccolumns.persistences.models.dto.ColumnsInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.Columns;
import com.diceprojects.msvccolumns.services.ColumnsService;
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

    private final ColumnsService columnsService;

    public ColumnsController(ColumnsService columnsService) {
        this.columnsService = columnsService;
    }

    @GetMapping("/get-config-columns")
    public ResponseEntity<Columns> getConfigColumnFromFileName(@RequestParam("fileName") String fileName) {

        Optional<Columns> o = columnsService.getConfigColumnFromFileName(fileName);

        if(o.isEmpty()){
            return ResponseEntity.
                    noContent().
                    build();
        }

        Columns column = o.get();
        return ResponseEntity
                .ok()
                .body(column);

    }

    @GetMapping("/list-all-columns")
    public ResponseEntity <List<Columns>> listAllColumns() {

        Optional<List<Columns>> o = columnsService.findAll();

        if(o.isEmpty()){
            return ResponseEntity.
                    noContent().
                    build();
        }

        List<Columns> columnsList = o.get();
        return ResponseEntity.
                ok().
                body(columnsList);

    }

    @GetMapping("/find-columns_by_operacion")
    public ResponseEntity<Columns> findByOperacionProcesoMapping(@RequestParam("operacion") String operacion) {

        Optional<Columns> o = columnsService.findByOperacionProcesoMapping(operacion);

        if(o.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        Columns column = o.get();
        return ResponseEntity.ok().body(column);

    }

    @PostMapping("/create_columns")
    public ResponseEntity<?> createColumns(@Valid @RequestBody ColumnsInDTO columnsInDTO) {

        Optional<Columns> o = columnsService.createColumns(columnsInDTO);

        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus
                    .NO_CONTENT).build();
        }

        Columns columns = o.get();

        return ResponseEntity.status(HttpStatus
                .CREATED).body(columns);

    }

}
