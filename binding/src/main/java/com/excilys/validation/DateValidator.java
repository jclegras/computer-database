package com.excilys.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateValidator implements ConstraintValidator<Date, String> {

	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(Date constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return value.matches(messageSource.getMessage(
				"validation.date.pattern", null,
				LocaleContextHolder.getLocale()));
	}

}
