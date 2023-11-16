package com.diceprojects.msvccolumns.persistences.models.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * DTO (Data Transfer Object) que representa la entrada de la cabecera que contiene la configuración de importación de las columnas de un archivo.
 */
@Data
public class FileColumnsHeaderInDTO {

        @Length(min=1, max=1)
        public String delimitadorArchivoMapping;

        public String startFile;

        public String operacionProcesoMapping;

        public String tipoEntidadMapping;

        public String tipoOperacionProcesoMapping;

}
