package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class GetAllCompanies implements ICommand {
	
	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		store.setCompanies(ctx.getWebService().getAllCompanies());
		System.out.println(store.getCompanies());
	}

}
