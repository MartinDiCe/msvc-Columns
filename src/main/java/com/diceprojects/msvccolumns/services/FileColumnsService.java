package com.diceprojects.msvccolumns.services;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsInDTO;
import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;

import java.util.List;
import java.util.Optional;

public interface FileColumnsService {
    Optional<FileColumnsDetails> getConfigColumnFromFileName(String fileName);
    Optional<List<FileColumnsHeader>> findAll();
    Optional<FileColumnsHeader> findByOperacionProcesoMapping(String operacion);
    FileColumnsHeader saveColumns(FileColumnsHeader fileColumnsHeader, FileColumnsDetails fileColumnsDetails);
    Optional<FileColumnsHeader>  createColumns(FileColumnsHeaderInDTO fileColumnsHeader, FileColumnsDetailsInDTO fileColumnsDetailsInDTO);
    Optional <FileColumnsHeader> findById(Long id);
    Optional<FileColumnsHeader> update(Long id, FileColumnsHeaderInDTO fileColumnsHeaderInDTO, FileColumnsDetailsInDTO fileColumnsDetailsInDTO);
    Optional<?> deleteFileColumns (Long id);

}