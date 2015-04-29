package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class Help implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		final StringBuilder sb = new StringBuilder();
		sb.append("== Commands ==\n\n");
		for (CommandToken command : CommandToken.values()) {
			sb.append(command + "\n");
		}
		System.out.println(sb);
	}

}
