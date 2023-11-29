package com.diceprojects.msvccolumns.persistences.models.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) que representa la lista de entradas de la config para procesar las columnas de un archivo.
 */
@Data
public class FileColumnsHeaderListDTO {

        public Long id;

        @Length(min=1, max=1)
        public String delimitadorArchivoMapping;

        public String startFile;

        public String operacionProcesoMapping;

        public String tipoEntidadMapping;

        public String tipoOperacionProcesoMapping;

        public Boolean ignoreFirstRowMapping;

        public LocalDateTime createDate;

}
