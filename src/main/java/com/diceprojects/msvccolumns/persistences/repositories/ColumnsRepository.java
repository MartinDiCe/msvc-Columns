package com.diceprojects.msvccolumns.persistences.repositories;

import com.diceprojects.msvccolumns.persistences.models.entities.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColumnsRepository extends JpaRepository<Columns, Long> {
    Optional<Columns> findByOperacionProcesoMapping(String operacion);
    Optional<Columns> findByStartFile(String startFile);
    List<Columns> findAll();
    Optional<Columns> findById(Long id);
    Optional<Columns> findByOperacionProcesoMappingAndTipoOperacionProcesoMapping
            (String operacionProcesoMapping, String tipoOperacionProcesoMapping);
    Optional<Columns> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMapping
            (String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping);
    Optional<Columns> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFile
            (String operacionProcesoMapping, String tipoOperacionProcesoMapping
                    , String tipoEntidadMapping, String startFile);


    Optional<Columns> findByStartFileAndIdNot(String startFile, Long id);

    Optional<Columns> findByOperacionProcesoMappingAndIdNot(String operacionProcesoMapping, Long id);

    Optional<Columns> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndIdNot(String operacionProcesoMapping, String tipoOperacionProcesoMapping, Long id);

    Optional<Columns> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndIdNot(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, Long id);

    Optional<Columns> findByOperacionProcesoMappingAndTipoOperacionProcesoMappingAndTipoEntidadMappingAndStartFileAndIdNot(String operacionProcesoMapping, String tipoOperacionProcesoMapping, String tipoEntidadMapping, String startFile, Long id);
}

