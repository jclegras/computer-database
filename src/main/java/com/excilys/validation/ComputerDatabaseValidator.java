package com.excilys.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public enum ComputerDatabaseValidator {
	INSTANCE;
	
	/**
	 * Validate date.
	 *
	 * @param inputString the input string
	 * @return true, if successful
	 */
	public boolean validateDate(String inputString) {

		SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			format.parse(inputString);
			Pattern p = Pattern
					.compile("^\\d{4}[-]?\\d{1,2}[-]?\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");
			return p.matcher(inputString).matches();
		} catch (ParseException e) {
			System.out.println("ParseException");
			return false;
		}
	}

}

