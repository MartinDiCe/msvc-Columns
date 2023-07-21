package com.diceprojects.msvccolumns.mapper;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import org.springframework.stereotype.Component;

@Component
public class FileColumnsHeaderInDTOColumns implements IMapper<FileColumnsHeaderInDTO, FileColumnsHeader> {

    public FileColumnsHeader map(FileColumnsHeaderInDTO in) {

        FileColumnsHeader fileColumnsHeader = new FileColumnsHeader();

        fileColumnsHeader.setDelimitadorArchivoMapping(in.getDelimitadorArchivoMapping());
        fileColumnsHeader.setStartFile(in.getStartFile());
        fileColumnsHeader.setOperacionProcesoMapping(in.getOperacionProcesoMapping());
        fileColumnsHeader.setTipoEntidadMapping(in.getTipoEntidadMapping());
        fileColumnsHeader.setTipoOperacionProcesoMapping(in.getTipoOperacionProcesoMapping());

        return fileColumnsHeader;

    }

}
