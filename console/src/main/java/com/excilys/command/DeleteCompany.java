package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class DeleteCompany implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		System.out.println("ID: ");
		if (ctx.getScanner().hasNextToken()) {
			ctx.setId(Long.valueOf(ctx.getScanner().getNextToken()));
		}
		ctx.getWebService().deleteCompany(ctx.getId());
		System.out.println("Successfull deletion");
	}

}
