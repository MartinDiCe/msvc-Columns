package com.diceprojects.msvccolumns.persistences.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "File_columns_header")
public class FileColumnsHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delimitador_archivo", length = 1)
    @NotNull
    @Length(min = 1, max = 1)
    private String delimitadorArchivoMapping;

    @Column(name = "file_start", nullable = false)
    @NotEmpty
    private String startFile;

    @Column(name = "operacion_proceso", nullable = false)
    @NotEmpty
    private String operacionProcesoMapping;

    @Column(name = "tipo_entidad", nullable = false)
    @NotEmpty
    private String tipoEntidadMapping;

    @Column(name = "tipo_operacion_proceso", nullable = false)
    @NotEmpty
    private String tipoOperacionProcesoMapping;

    @OneToOne(mappedBy = "fileColumnsHeader", cascade = CascadeType.ALL)
    private FileColumnsDetails fileColumnsDetails;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public FileColumnsHeader() {
    }
}