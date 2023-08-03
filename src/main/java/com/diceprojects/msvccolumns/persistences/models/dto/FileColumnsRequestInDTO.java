package com.diceprojects.msvccolumns.persistences.models.dto;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * DTO (Data Transfer Object) que representa la entrada para los request completos de las columnas de un archivo.
 */
@Data
public class FileColumnsRequestInDTO {

    @Valid
    public FileColumnsHeaderInDTO fileColumnsHeaderInDTO;

    @Valid
    public FileColumnsDetailsInDTO fileColumnsDetailsInDTO;

}
