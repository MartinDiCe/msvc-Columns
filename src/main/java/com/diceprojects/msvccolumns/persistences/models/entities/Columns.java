package com.diceprojects.msvccolumns.persistences.models.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "columns_mapping",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"file_start","operacion_proceso","tipo_entidad","tipo_operacion_proceso"})})
public class Columns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "column0")
    private Integer column0Mapping;

    @Column(name = "name_column0")
    private String nameColumn0;

    @Column(name = "column1")
    private Integer column1Mapping;

    @Column(name = "name_column1")
    private String nameColumn1;

    @Column(name = "column2")
    private Integer column2Mapping;

    @Column(name = "name_column2")
    private String nameColumn2;

    @Column(name = "column3")
    private Integer column3Mapping;

    @Column(name = "name_column3")
    private String nameColumn3;

    @Column(name = "column4")
    private Integer column4Mapping;

    @Column(name = "name_column4")
    private String nameColumn4;

    @Column(name = "column5")
    private Integer column5Mapping;

    @Column(name = "name_column5")
    private String nameColumn5;

    @Column(name = "column6")
    private Integer column6Mapping;

    @Column(name = "name_column6")
    private String nameColumn6;

    @Column(name = "column7")
    private Integer column7Mapping;

    @Column(name = "name_column7")
    private String nameColumn7;

    @Column(name = "column8")
    private Integer column8Mapping;

    @Column(name = "name_column8")
    private String nameColumn8;

    @Column(name = "column9")
    private Integer column9Mapping;

    @Column(name = "name_column9")
    private String nameColumn9;

    @Column(name = "column10")
    private Integer column10Mapping;

    @Column(name = "name_column10")
    private String nameColumn10;

    @Column(name = "column11")
    private Integer column11Mapping;

    @Column(name = "name_column11")
    private String nameColumn11;

    @Column(name = "column12")
    private Integer column12Mapping;

    @Column(name = "name_column12")
    private String nameColumn12;

    @Column(name = "column13")
    private Integer column13Mapping;

    @Column(name = "name_column13")
    private String nameColumn13;

    @Column(name = "column14")
    private Integer column14Mapping;

    @Column(name = "name_column14")
    private String nameColumn14;

    @Column(name = "column15")
    private Integer column15Mapping;

    @Column(name = "name_column15")
    private String nameColumn15;

    @Column(name = "column16")
    private Integer column16Mapping;

    @Column(name = "name_column16")
    private String nameColumn16;

    @Column(name = "column17")
    private Integer column17Mapping;

    @Column(name = "name_column17")
    private String nameColumn17;

    @Column(name = "column18")
    private Integer column18Mapping;

    @Column(name = "name_column18")
    private String nameColumn18;

    @Column(name = "column19")
    private Integer column19Mapping;

    @Column(name = "name_column19")
    private String nameColumn19;

    @Column(name = "column20")
    private Integer column20Mapping;

    @Column(name = "name_column20")
    private String nameColumn20;

    @Column(name = "column21")
    private Integer column21Mapping;

    @Column(name = "name_column21")
    private String nameColumn21;

    @Column(name = "column22")
    private Integer column22Mapping;

    @Column(name = "name_column22")
    private String nameColumn22;

    @Column(name = "column23")
    private Integer column23Mapping;

    @Column(name = "name_column23")
    private String nameColumn23;

    @Column(name = "column24")
    private Integer column24Mapping;

    @Column(name = "name_column24")
    private String nameColumn24;

    @Column(name = "column25")
    private Integer column25Mapping;

    @Column(name = "name_column25")
    private String nameColumn25;

    @Column(name = "column26")
    private Integer column26Mapping;

    @Column(name = "name_column26")
    private String nameColumn26;

    @Column(name = "column27")
    private Integer column27Mapping;

    @Column(name = "name_column27")
    private String nameColumn27;

    @Column(name = "column28")
    private Integer column28Mapping;

    @Column(name = "name_column28")
    private String nameColumn28;

    @Column(name = "column29")
    private Integer column29Mapping;

    @Column(name = "name_column29")
    private String nameColumn29;

    @Column(name = "column30")
    private Integer column30Mapping;

    @Column(name = "name_column30")
    private String nameColumn30;

    @Column(name = "column31")
    private Integer column31Mapping;

    @Column(name = "name_column31")
    private String nameColumn31;

    @Column(name = "column32")
    private Integer column32Mapping;

    @Column(name = "name_column32")
    private String nameColumn32;

    @Column(name = "column33")
    private Integer column33Mapping;

    @Column(name = "name_column33")
    private String nameColumn33;

    @Column(name = "column34")
    private Integer column34Mapping;

    @Column(name = "name_column34")
    private String nameColumn34;

    @Column(name = "column35")
    private Integer column35Mapping;

    @Column(name = "name_column35")
    private String nameColumn35;

    @Column(name = "column36")
    private Integer column36Mapping;

    @Column(name = "name_column36")
    private String nameColumn36;

    @Column(name = "column37")
    private Integer column37Mapping;

    @Column(name = "name_column37")
    private String nameColumn37;

    @Column(name = "column38")
    private Integer column38Mapping;

    @Column(name = "name_column38")
    private String nameColumn38;

    @Column(name = "column39")
    private Integer column39Mapping;

    @Column(name = "name_column39")
    private String nameColumn39;

    @Column(name = "column40")
    private Integer column40Mapping;

    @Column(name = "name_column40")
    private String nameColumn40;

    @Column(name = "delimitador_archivo")
    @NotBlank
    private char delimitadorArchivoMapping;

    @Column(name = "file_start", nullable = false)
    @NotBlank
    private String startFile;

    @Column(name = "operacion_proceso", nullable = false)
    @NotBlank
    private String operacionProcesoMapping;

    @Column(name = "tipo_entidad", nullable = false)
    @NotBlank
    private String tipoEntidadMapping;

    @Column(name = "tipo_operacion_proceso", nullable = false)
    @NotBlank
    private String tipoOperacionProcesoMapping;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public Columns() {
    }
}