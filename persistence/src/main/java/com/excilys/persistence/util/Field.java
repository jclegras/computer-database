package com.excilys.persistence.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Valid naming fields for models.
 */
public enum Field {
	COMPUTER_ID("computer.id"),
	COMPUTER_NAME("computer.name"),
	COMPUTER_INTRODUCED("computer.introduced"),
	COMPUTER_DISCONTINUED("computer.discontinued"),
	COMPANY_ID("company.id"),
	COMPANY_NAME("company.name");
	
	private static Set<String> names;
	static {
		names = new HashSet<>();
		for (Field field : Field.values()) {
			names.add(field.name);
		}
	}
	private String name;
	
	Field(String name) {
		this.name = name;
	}
	
	public static boolean isValid(String name) {
		return names.contains(name);
	}

    @Override
    public String toString() {
        return name;
    }
}
