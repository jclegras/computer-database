package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeConverter {
	private static final String PATTERN_CODE_FULL = "validation.format.full";
	private static final String PATTERN_CODE_SHORT = "validation.format.short";
	private static final String DEFAULT_TIME = "00:00:00";
	private static final String SEPARATOR = " ";
	@Autowired
	private MessageSource messageSource;

	/**
	 * Converts a simple date to matching LocalDateTime.
	 *
	 * @param inputString Date in plain text
	 * @return matching <code>LocalDateTime</code>
	 */
	public LocalDateTime convertToValidLocalDateTime(String inputString) {
		final String datePattern = messageSource.getMessage(
				PATTERN_CODE_FULL, null, LocaleContextHolder.getLocale());
		final DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern(datePattern);
		return LocalDateTime.parse(inputString + SEPARATOR + DEFAULT_TIME, formatter);
	}
	
	/**
	 * Converts the given date to matching text.
	 * 
	 * @param localDateTime Date
	 * @return text
	 */
	public String convertToText(LocalDateTime localDateTime) {
		final String datePattern = messageSource.getMessage(
				PATTERN_CODE_SHORT, null, LocaleContextHolder.getLocale());
		final DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern(datePattern);
		return localDateTime.format(formatter);
	}
}
