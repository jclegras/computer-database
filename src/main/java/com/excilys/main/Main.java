package com.excilys.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.util.Command;
import com.excilys.util.ComputerDatabaseContext;
import com.excilys.util.ComputerDatabaseScanner;
import com.excilys.validation.ComputerDatabaseValidator;

public class Main {
	public static void main(String[] args) throws Exception {
		// final Page page = new Page(2, 5);
		// System.out.println(ComputerService.INSTANCE.getAll(page));
		ComputerDatabaseScanner scanner = new ComputerDatabaseScanner();
		ComputerDatabaseContext ctx = new ComputerDatabaseContext();
		while (!scanner.isExit()) {
			final String token = scanner.getNextToken();
			if ("exit".equals(token)) {
				scanner.setExit(true);
			}
			final Command command = Command.getCommand(token);
			if (command != null) {
				if (command.equals(Command.GET_BY_ID_COMPUTER)) {
					System.out.print("Identifier : ");
					ctx.setComputerId(Long.valueOf(scanner.getNextToken()));
				} else if (command.equals(Command.DELETE_COMPUTER)) {
					System.out.print("Identifier : ");
					ctx.setComputerId(Long.valueOf(scanner.getNextToken()));
				} else if (command.equals(Command.UPDATE_COMPUTER)) {
					System.out.println("Identifier : ");
					final Computer computer = ComputerService.INSTANCE
							.getById(Long.valueOf(scanner.getNextToken()));
					System.out.println("name : ");
					if (scanner.hasNextToken()) {
						computer.setName(scanner.getNextToken());
					}
					System.out.println("Introduced :");
					if (scanner.hasNextToken()) {
						final String tok = scanner.getNextToken();
						final StringBuilder sb = new StringBuilder();
						sb.append(tok).append(" ").append("00:00:00");
						if (ComputerDatabaseValidator.INSTANCE.validateDate(sb
								.toString())) {
							DateTimeFormatter formatter = DateTimeFormatter
									.ofPattern("yyyy-MM-dd HH:mm:ss");
							LocalDateTime dateTime = LocalDateTime.parse(
									sb.toString(), formatter);
							computer.setIntroduced(dateTime);
						}
					}
					System.out.println("Discontinued :");
					if (scanner.hasNextToken()) {
						final String tok = scanner.getNextToken();
						final StringBuilder sb = new StringBuilder();
						sb.append(tok).append(" ").append("00:00:00");
						if (ComputerDatabaseValidator.INSTANCE.validateDate(sb
								.toString())) {
							DateTimeFormatter formatter = DateTimeFormatter
									.ofPattern("yyyy-MM-dd HH:mm:ss");
							LocalDateTime dateTime = LocalDateTime.parse(
									sb.toString(), formatter);
							computer.setDiscontinued(dateTime);
						}
					}
					System.out.println("Company id");
					if (scanner.hasNextToken()) {
						computer.setCompanyId(Long.valueOf(scanner
								.getNextToken()));
					}
					ctx.setNewComputer(computer);
				}
				command.execute(ctx);
			}
		}
	}
}
