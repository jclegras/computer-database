package com.excilys.model.converter;

import java.util.HashSet;
import java.util.Set;

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
	
	private Field(String name) {
		this.name = name;
	}
	
	public static boolean isValid(String name) {
		return names.contains(name);
	}
}
