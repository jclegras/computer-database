package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class GetByIdCompany implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		System.out.println("ID: ");
		if (ctx.getScanner().hasNextToken()) {
			ctx.setId(Long.valueOf(ctx.getScanner().getNextToken()));
		}
		store.setCompany(ctx.getWebService().getByIdCompany(ctx.getId()));
		System.out.println(store.getCompany());
	}

}
