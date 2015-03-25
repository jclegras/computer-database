package com.excilys.exception;

public enum ExceptionMessage {
	WRONG_ID("ID must be not null and > 0"),
	ARG_NULL("Argument must be not null");
	
	private final String message;
	
	ExceptionMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
