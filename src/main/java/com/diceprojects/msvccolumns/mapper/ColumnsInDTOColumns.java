package com.diceprojects.msvccolumns.mapper;

import com.diceprojects.msvccolumns.persistences.models.dto.ColumnsInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.Columns;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ColumnsInDTOColumns implements IMapper<ColumnsInDTO, Columns> {

    public Columns map(ColumnsInDTO in) {

        Columns columns = new Columns();

        columns.setColumn0Mapping(in.getColumn0Mapping());
        columns.setNameColumn0(in.getNameColumn0());
        columns.setColumn1Mapping(in.getColumn1Mapping());
        columns.setNameColumn1(in.getNameColumn1());
        columns.setColumn2Mapping(in.getColumn2Mapping());
        columns.setNameColumn2(in.getNameColumn2());
        columns.setColumn3Mapping(in.getColumn3Mapping());
        columns.setNameColumn3(in.getNameColumn3());
        columns.setColumn4Mapping(in.getColumn4Mapping());
        columns.setNameColumn4(in.getNameColumn4());
        columns.setColumn5Mapping(in.getColumn5Mapping());
        columns.setNameColumn5(in.getNameColumn5());
        columns.setColumn6Mapping(in.getColumn6Mapping());
        columns.setNameColumn6(in.getNameColumn6());
        columns.setColumn7Mapping(in.getColumn7Mapping());
        columns.setNameColumn7(in.getNameColumn7());
        columns.setColumn8Mapping(in.getColumn8Mapping());
        columns.setNameColumn8(in.getNameColumn8());
        columns.setColumn9Mapping(in.getColumn9Mapping());
        columns.setNameColumn9(in.getNameColumn9());
        columns.setColumn10Mapping(in.getColumn10Mapping());
        columns.setNameColumn10(in.getNameColumn10());
        columns.setColumn11Mapping(in.getColumn11Mapping());
        columns.setNameColumn11(in.getNameColumn11());
        columns.setColumn12Mapping(in.getColumn12Mapping());
        columns.setNameColumn12(in.getNameColumn12());
        columns.setColumn13Mapping(in.getColumn13Mapping());
        columns.setNameColumn13(in.getNameColumn13());
        columns.setColumn14Mapping(in.getColumn14Mapping());
        columns.setNameColumn14(in.getNameColumn14());
        columns.setColumn15Mapping(in.getColumn15Mapping());
        columns.setNameColumn15(in.getNameColumn15());
        columns.setColumn16Mapping(in.getColumn16Mapping());
        columns.setNameColumn16(in.getNameColumn16());
        columns.setColumn17Mapping(in.getColumn17Mapping());
        columns.setNameColumn17(in.getNameColumn17());
        columns.setColumn18Mapping(in.getColumn18Mapping());
        columns.setNameColumn18(in.getNameColumn18());
        columns.setColumn19Mapping(in.getColumn19Mapping());
        columns.setNameColumn19(in.getNameColumn19());
        columns.setColumn20Mapping(in.getColumn20Mapping());
        columns.setNameColumn20(in.getNameColumn20());
        columns.setColumn21Mapping(in.getColumn21Mapping());
        columns.setNameColumn21(in.getNameColumn21());
        columns.setColumn22Mapping(in.getColumn22Mapping());
        columns.setNameColumn22(in.getNameColumn22());
        columns.setColumn23Mapping(in.getColumn23Mapping());
        columns.setNameColumn23(in.getNameColumn23());
        columns.setColumn24Mapping(in.getColumn24Mapping());
        columns.setNameColumn24(in.getNameColumn24());
        columns.setColumn25Mapping(in.getColumn25Mapping());
        columns.setNameColumn25(in.getNameColumn25());
        columns.setColumn26Mapping(in.getColumn26Mapping());
        columns.setNameColumn26(in.getNameColumn26());
        columns.setColumn27Mapping(in.getColumn27Mapping());
        columns.setNameColumn27(in.getNameColumn27());
        columns.setColumn28Mapping(in.getColumn28Mapping());
        columns.setNameColumn28(in.getNameColumn28());
        columns.setColumn29Mapping(in.getColumn29Mapping());
        columns.setNameColumn29(in.getNameColumn29());
        columns.setColumn30Mapping(in.getColumn30Mapping());
        columns.setNameColumn30(in.getNameColumn30());
        columns.setColumn31Mapping(in.getColumn31Mapping());
        columns.setNameColumn31(in.getNameColumn31());
        columns.setColumn32Mapping(in.getColumn32Mapping());
        columns.setNameColumn32(in.getNameColumn32());
        columns.setColumn33Mapping(in.getColumn33Mapping());
        columns.setNameColumn33(in.getNameColumn33());
        columns.setColumn34Mapping(in.getColumn34Mapping());
        columns.setNameColumn34(in.getNameColumn34());
        columns.setColumn35Mapping(in.getColumn35Mapping());
        columns.setNameColumn35(in.getNameColumn35());
        columns.setColumn36Mapping(in.getColumn36Mapping());
        columns.setNameColumn36(in.getNameColumn36());
        columns.setColumn37Mapping(in.getColumn37Mapping());
        columns.setNameColumn37(in.getNameColumn37());
        columns.setColumn38Mapping(in.getColumn38Mapping());
        columns.setNameColumn38(in.getNameColumn38());
        columns.setColumn39Mapping(in.getColumn39Mapping());
        columns.setNameColumn39(in.getNameColumn39());
        columns.setColumn40Mapping(in.getColumn40Mapping());
        columns.setNameColumn40(in.getNameColumn40());
        columns.setDelimitadorArchivoMapping(in.getDelimitadorArchivoMapping());
        columns.setStartFile(in.getStartFile());
        columns.setOperacionProcesoMapping(in.getOperacionProcesoMapping());
        columns.setTipoEntidadMapping(in.getTipoEntidadMapping());
        columns.setTipoOperacionProcesoMapping(in.getTipoOperacionProcesoMapping());
        columns.setCreateDate(columns.getCreateDate());
        columns.setUpdateDate(columns.getUpdateDate());

        return columns;

    }

}
