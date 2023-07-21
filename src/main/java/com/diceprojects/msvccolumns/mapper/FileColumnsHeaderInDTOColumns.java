package com.diceprojects.msvccolumns.mapper;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsHeaderInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsHeader;
import org.springframework.stereotype.Component;

@Component
public class ColumnsInDTOColumns implements IMapper<FileColumnsHeaderInDTO, FileColumnsHeader> {

    public FileColumnsHeader map(FileColumnsHeaderInDTO in) {

        FileColumnsHeader fileColumnsHeader = new FileColumnsHeader();

        fileColumnsHeader.setColumn0Mapping(in.getColumn0Mapping());
        fileColumnsHeader.setNameColumn0(in.getNameColumn0());
        fileColumnsHeader.setColumn1Mapping(in.getColumn1Mapping());
        fileColumnsHeader.setNameColumn1(in.getNameColumn1());
        fileColumnsHeader.setColumn2Mapping(in.getColumn2Mapping());
        fileColumnsHeader.setNameColumn2(in.getNameColumn2());
        fileColumnsHeader.setColumn3Mapping(in.getColumn3Mapping());
        fileColumnsHeader.setNameColumn3(in.getNameColumn3());
        fileColumnsHeader.setColumn4Mapping(in.getColumn4Mapping());
        fileColumnsHeader.setNameColumn4(in.getNameColumn4());
        fileColumnsHeader.setColumn5Mapping(in.getColumn5Mapping());
        fileColumnsHeader.setNameColumn5(in.getNameColumn5());
        fileColumnsHeader.setColumn6Mapping(in.getColumn6Mapping());
        fileColumnsHeader.setNameColumn6(in.getNameColumn6());
        fileColumnsHeader.setColumn7Mapping(in.getColumn7Mapping());
        fileColumnsHeader.setNameColumn7(in.getNameColumn7());
        fileColumnsHeader.setColumn8Mapping(in.getColumn8Mapping());
        fileColumnsHeader.setNameColumn8(in.getNameColumn8());
        fileColumnsHeader.setColumn9Mapping(in.getColumn9Mapping());
        fileColumnsHeader.setNameColumn9(in.getNameColumn9());
        fileColumnsHeader.setColumn10Mapping(in.getColumn10Mapping());
        fileColumnsHeader.setNameColumn10(in.getNameColumn10());
        fileColumnsHeader.setColumn11Mapping(in.getColumn11Mapping());
        fileColumnsHeader.setNameColumn11(in.getNameColumn11());
        fileColumnsHeader.setColumn12Mapping(in.getColumn12Mapping());
        fileColumnsHeader.setNameColumn12(in.getNameColumn12());
        fileColumnsHeader.setColumn13Mapping(in.getColumn13Mapping());
        fileColumnsHeader.setNameColumn13(in.getNameColumn13());
        fileColumnsHeader.setColumn14Mapping(in.getColumn14Mapping());
        fileColumnsHeader.setNameColumn14(in.getNameColumn14());
        fileColumnsHeader.setColumn15Mapping(in.getColumn15Mapping());
        fileColumnsHeader.setNameColumn15(in.getNameColumn15());
        fileColumnsHeader.setColumn16Mapping(in.getColumn16Mapping());
        fileColumnsHeader.setNameColumn16(in.getNameColumn16());
        fileColumnsHeader.setColumn17Mapping(in.getColumn17Mapping());
        fileColumnsHeader.setNameColumn17(in.getNameColumn17());
        fileColumnsHeader.setColumn18Mapping(in.getColumn18Mapping());
        fileColumnsHeader.setNameColumn18(in.getNameColumn18());
        fileColumnsHeader.setColumn19Mapping(in.getColumn19Mapping());
        fileColumnsHeader.setNameColumn19(in.getNameColumn19());
        fileColumnsHeader.setColumn20Mapping(in.getColumn20Mapping());
        fileColumnsHeader.setNameColumn20(in.getNameColumn20());
        fileColumnsHeader.setColumn21Mapping(in.getColumn21Mapping());
        fileColumnsHeader.setNameColumn21(in.getNameColumn21());
        fileColumnsHeader.setColumn22Mapping(in.getColumn22Mapping());
        fileColumnsHeader.setNameColumn22(in.getNameColumn22());
        fileColumnsHeader.setColumn23Mapping(in.getColumn23Mapping());
        fileColumnsHeader.setNameColumn23(in.getNameColumn23());
        fileColumnsHeader.setColumn24Mapping(in.getColumn24Mapping());
        fileColumnsHeader.setNameColumn24(in.getNameColumn24());
        fileColumnsHeader.setColumn25Mapping(in.getColumn25Mapping());
        fileColumnsHeader.setNameColumn25(in.getNameColumn25());
        fileColumnsHeader.setColumn26Mapping(in.getColumn26Mapping());
        fileColumnsHeader.setNameColumn26(in.getNameColumn26());
        fileColumnsHeader.setColumn27Mapping(in.getColumn27Mapping());
        fileColumnsHeader.setNameColumn27(in.getNameColumn27());
        fileColumnsHeader.setColumn28Mapping(in.getColumn28Mapping());
        fileColumnsHeader.setNameColumn28(in.getNameColumn28());
        fileColumnsHeader.setColumn29Mapping(in.getColumn29Mapping());
        fileColumnsHeader.setNameColumn29(in.getNameColumn29());
        fileColumnsHeader.setColumn30Mapping(in.getColumn30Mapping());
        fileColumnsHeader.setNameColumn30(in.getNameColumn30());
        fileColumnsHeader.setColumn31Mapping(in.getColumn31Mapping());
        fileColumnsHeader.setNameColumn31(in.getNameColumn31());
        fileColumnsHeader.setColumn32Mapping(in.getColumn32Mapping());
        fileColumnsHeader.setNameColumn32(in.getNameColumn32());
        fileColumnsHeader.setColumn33Mapping(in.getColumn33Mapping());
        fileColumnsHeader.setNameColumn33(in.getNameColumn33());
        fileColumnsHeader.setColumn34Mapping(in.getColumn34Mapping());
        fileColumnsHeader.setNameColumn34(in.getNameColumn34());
        fileColumnsHeader.setColumn35Mapping(in.getColumn35Mapping());
        fileColumnsHeader.setNameColumn35(in.getNameColumn35());
        fileColumnsHeader.setColumn36Mapping(in.getColumn36Mapping());
        fileColumnsHeader.setNameColumn36(in.getNameColumn36());
        fileColumnsHeader.setColumn37Mapping(in.getColumn37Mapping());
        fileColumnsHeader.setNameColumn37(in.getNameColumn37());
        fileColumnsHeader.setColumn38Mapping(in.getColumn38Mapping());
        fileColumnsHeader.setNameColumn38(in.getNameColumn38());
        fileColumnsHeader.setColumn39Mapping(in.getColumn39Mapping());
        fileColumnsHeader.setNameColumn39(in.getNameColumn39());
        fileColumnsHeader.setColumn40Mapping(in.getColumn40Mapping());
        fileColumnsHeader.setNameColumn40(in.getNameColumn40());
        fileColumnsHeader.setDelimitadorArchivoMapping(in.getDelimitadorArchivoMapping());
        fileColumnsHeader.setStartFile(in.getStartFile());
        fileColumnsHeader.setOperacionProcesoMapping(in.getOperacionProcesoMapping());
        fileColumnsHeader.setTipoEntidadMapping(in.getTipoEntidadMapping());
        fileColumnsHeader.setTipoOperacionProcesoMapping(in.getTipoOperacionProcesoMapping());
        fileColumnsHeader.setCreateDate(fileColumnsHeader.getCreateDate());
        fileColumnsHeader.setUpdateDate(fileColumnsHeader.getUpdateDate());

        return fileColumnsHeader;

    }

}
