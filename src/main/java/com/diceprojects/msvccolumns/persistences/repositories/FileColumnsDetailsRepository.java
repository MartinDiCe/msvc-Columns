package com.diceprojects.msvccolumns.persistences.repositories;

import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FileColumnsDetailsRepository extends JpaRepository<FileColumnsDetails, Long> {
        Optional<FileColumnsDetails> findByFileColumnsHeaderId(Long fileColumnsHeaderId);
        List<FileColumnsDetails> findAll();
        Optional<FileColumnsDetails> findById(Long id);

        FileColumnsDetails findByFileColumnsHeader(FileColumnsHeader fileColumnsHeader);

}