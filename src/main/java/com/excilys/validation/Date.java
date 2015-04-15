package com.excilys.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * Custom annotation @Date to validate the date variable.
 * 
 */
@Constraint(validatedBy = DateValidator.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {

	String message() default "{validation.date.invalidFormat}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default { };
}
