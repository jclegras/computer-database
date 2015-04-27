package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class DeleteComputer implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		System.out.println("ID: ");
		if (ctx.getScanner().hasNextToken()) {
			ctx.setId(Long.valueOf(ctx.getScanner().getNextToken()));
		}
		ctx.getWebService().deleteComputer(ctx.getId());
		System.out.println("Successfull deletion");
	}

}
