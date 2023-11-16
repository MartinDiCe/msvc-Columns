package com.diceprojects.msvccolumns.mapper;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de mapear un objeto de tipo FileColumnsHeaderInDTO a un objeto de tipo FileColumnsHeader.
 */
@Component
public class FileColumnsHeaderInDTOColumns implements IMapper<FileColumnsHeaderInDTO, FileColumnsHeader> {

    /**
     * Realiza el mapeo de un objeto de tipo FileColumnsHeaderInDTO a un objeto de tipo FileColumnsHeader.
     *
     * @param in El objeto de tipo FileColumnsHeaderInDTO a mapear.
     * @return El objeto de tipo FileColumnsHeader resultante del mapeo.
     */
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
