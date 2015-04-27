package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class GetByName implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		System.out.println("Computer or company's name: ");
		if (ctx.getScanner().hasNextToken()) {
			ctx.setName(ctx.getScanner().getNextToken());
		}
		store.setComputers(ctx.getWebService().getByName(ctx.getName()));
		System.out.println(store.getComputers());
	}

}
