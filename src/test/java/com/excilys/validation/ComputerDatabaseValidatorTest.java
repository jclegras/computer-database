package com.excilys.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ComputerDatabaseValidatorTest {

    @Test
    public void validateRightFormatDate() {
        // GIVEN
        final String date = "2000-12-05 00:00:00";

        // WHEN
        final boolean result = ComputerDatabaseValidator.INSTANCE
                .validateDateTime(date);

        // THEN
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void validateWrongFormatDate() {
        // GIVEN
        final String date = "2000-12-05";

        // WHEN
        final boolean result = ComputerDatabaseValidator.INSTANCE
                .validateDateTime(date);

        // THEN
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void validateSimpleDate() {
        // GIVEN
        final String date = "2000-12-05";

        // WHEN
        final boolean result = ComputerDatabaseValidator.INSTANCE
                .validateSimpleDate(date);

        // THEN
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void validateWrongDate() {
        // GIVEN
        final String date = "2000-12-05 ";

        // WHEN
        final boolean result = ComputerDatabaseValidator.INSTANCE
                .validateSimpleDate(date);

        // THEN
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void test() {
    	LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(LocalDateTime.parse("2014-10-05 00:00:00", formatter));
    }
}
