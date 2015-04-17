package com.excilys.model.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateTimePersistenceConverter implements
		AttributeConverter<LocalDateTime, java.sql.Timestamp> {

	@Override
	public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
		return (entityValue == null) ? null : Timestamp.valueOf(entityValue);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
		return (databaseValue == null) ? null : databaseValue.toLocalDateTime();
	}
}
