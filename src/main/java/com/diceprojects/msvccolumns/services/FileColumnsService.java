package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;

import java.util.List;
import java.util.Optional;

public interface ColumnsService {
    Optional<FileColumnsHeader> getConfigColumnFromFileName(String fileName);
    Optional<List<FileColumnsDTO>> findAll();
    Optional<FileColumnsDTO> findByOperacionProcesoMapping(String operacion);
    FileColumnsHeader saveColumns(FileColumnsHeader fileColumnsHeader, FileColumnsDetails fileColumnsDetails);
    Optional<FileColumnsDTO>  createColumns(FileColumnsHeaderInDTO fileColumnsHeader, FileColumnsDetailsInDTO fileColumnsDetailsInDTO);
    Optional <FileColumnsDTO> findById(Long id);
    Optional <FileColumnsHeader> update(Long id, FileColumnsHeaderInDTO fileColumnsHeaderInDTO, FileColumnsDetailsInDTO fileColumnsDetailsInDTO);


}