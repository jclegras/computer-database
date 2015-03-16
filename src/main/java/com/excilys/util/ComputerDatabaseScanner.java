package com.excilys.util;

import java.util.Scanner;

public class ComputerDatabaseScanner {
	private final Scanner scanner;
	private boolean exit;
	
	public ComputerDatabaseScanner() {
		scanner = new Scanner(System.in);
	}
	
	public String getNextToken() {
		return scanner.hasNext() ? scanner.next() : null; 
	}
	
	public boolean isExit() {
		return exit;
	}
	
	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
