package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public class Count implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		store.setCount(ctx.getWebService().count());
		System.out.println(store.getCount());
	}

}
