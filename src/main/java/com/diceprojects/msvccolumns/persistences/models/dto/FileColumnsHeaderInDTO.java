package com.diceprojects.msvccolumns.persistences.models.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class FileColumnsHeaderInDTO {

        @Length(min=1, max=1)
        public String delimitadorArchivoMapping;

        public String startFile;

        public String operacionProcesoMapping;

        public String tipoEntidadMapping;

        public String tipoOperacionProcesoMapping;

}
