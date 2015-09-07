package com.laranevans.springrestreact.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

// Helper to handle converting to/from LocalDate. Hibernate doesn't handle it well by default.
@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
        return null == entityValue ? null : java.sql.Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
        return null == databaseValue ? null : databaseValue.toLocalDate();
    }
}