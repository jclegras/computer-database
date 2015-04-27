package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class UpdateComputer implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		ctx.getWebService().update(ctx.getComputerDTO());
		System.out.println("Successfull updating");
	}

}
