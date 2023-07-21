package com.diceprojects.msvccolumns.mapper;

import com.diceprojects.msvccolumns.persistences.models.dto.FileColumnsDetailsInDTO;
import com.diceprojects.msvccolumns.persistences.models.entities.FileColumnsDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class FileColumnsDetailsInDTOColumns implements IMapper<FileColumnsDetailsInDTO, FileColumnsDetails> {

    @Override
    public FileColumnsDetails map(FileColumnsDetailsInDTO in) {
        FileColumnsDetails fileColumnsDetails = new FileColumnsDetails();

        Field[] fields = in.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String setterName = "set" + fieldName.substring(0, 1)
                    .toUpperCase() + fieldName
                    .substring(1);

            try {
                Method setter = fileColumnsDetails.getClass().getMethod(setterName, field.getType());
                Object value = field.get(in);
                setter.invoke(fileColumnsDetails, value);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return fileColumnsDetails;
    }
}