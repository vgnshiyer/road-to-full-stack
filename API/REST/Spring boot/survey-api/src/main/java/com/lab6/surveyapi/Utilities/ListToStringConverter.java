package com.lab6.surveyapi.Utilities;

import java.util.List;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ListToStringConverter implements AttributeConverter<List<String>, String> {
    private static final String SEPARATOR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute == null ? null : String.join(SEPARATOR, attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return dbData == null ? null : List.of(dbData.split(SEPARATOR));
    }
}
