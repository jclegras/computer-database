package com.excilys.main;

import com.excilys.util.Command;
import com.excilys.util.ComputerDatabaseContext;
import com.excilys.util.ComputerDatabaseScanner;


public class Main {
	public static void main(String[] args) throws Exception {
//		final Page page = new Page(2, 5);
//		System.out.println(ComputerService.INSTANCE.getAll(page));
		ComputerDatabaseScanner scanner = new ComputerDatabaseScanner();
		ComputerDatabaseContext ctx = new ComputerDatabaseContext();
		while (!scanner.isExit()) {
			final String token = scanner.getNextToken();
			if ("exit".equals(token)) {
				scanner.setExit(true);
			}
			final Command command = Command.getCommand(token);
			if (command != null) {
				command.execute(ctx);
			}
		}
	}
}
