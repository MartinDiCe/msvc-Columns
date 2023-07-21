package com.diceprojects.msvccolumns.persistences.repositories;

import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileColumnsHeaderRepository extends JpaRepository<FileColumnsHeader, Long> {
    Optional<FileColumnsHeader> findByOperacionProcesoMapping(String operacion);
    Optional<FileColumnsHeader> findByStartFile(String startFile);
    List<FileColumnsHeader> findAll();
    Optional<FileColumnsHeader> findById(Long id);
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMapping
            (String operacionProcesoMapping, String tipoOperacionProcesoMapping);
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMapping
            (String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping);
    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFile
            (String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile);

    Optional<FileColumnsHeader> findByStartFileAndIdNot(String startFile, Long id);

    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndIdNot(String operacionProcesoMapping, Long id);

    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndIdNot(String operacionProcesoMapping, String tipoOperacionProcesoMapping, Long id);

    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndIdNot(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, Long id);

    Optional<FileColumnsHeader> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFileAndIdNot(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile, Long id);

}
