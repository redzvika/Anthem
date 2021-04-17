package com.anthem.db.converters;


import javax.persistence.AttributeConverter;
import java.sql.Timestamp;

public class EpochTimeConverter implements AttributeConverter<Long, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(Long attribute) {
        return attribute == null ? null : new Timestamp(attribute);
    }

    @Override
    public Long convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : dbData.getTime();
    }
}
