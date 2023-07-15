package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.persistences.models.dto.ColumnsInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.Columns;

import java.util.List;
import java.util.Optional;

public interface ColumnsService {
    Optional<Columns> getConfigColumnFromFileName(String fileName);
    Optional<List<Columns>> findAll();
    Optional<Columns> findByOperacionProcesoMapping(String operacion);
    Columns saveColumns(Columns columns);
    Optional<Columns> createColumns(ColumnsInDTO columns);

}