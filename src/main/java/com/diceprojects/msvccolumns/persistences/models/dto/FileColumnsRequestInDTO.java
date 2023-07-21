package com.diceprojects.msvccolumns.persistences.models.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class FileColumnsRequestInDTO {

    @Valid
    public FileColumnsHeaderInDTO fileColumnsHeaderInDTO;

    @Valid
    public FileColumnsDetailsInDTO fileColumnsDetailsInDTO;

}
