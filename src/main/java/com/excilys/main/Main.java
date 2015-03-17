package com.excilys.main;

import com.excilys.util.Command;
import com.excilys.util.ComputerDatabaseContext;
import com.excilys.util.ComputerDatabaseScanner;

public class Main {
	public static void main(String[] args) throws Exception {
		ComputerDatabaseScanner scanner = new ComputerDatabaseScanner();
		ComputerDatabaseContext ctx = new ComputerDatabaseContext();
		ctx.setScanner(scanner);

		while (!scanner.isExit()) {
			Command.getCommand(scanner.getNextToken()).execute(ctx);
		}
	}
}
