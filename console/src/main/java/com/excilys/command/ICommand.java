package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;

public interface ICommand {
	void execute(IComputerDatabaseStorage store, IComputerDatabaseContext ctx);
}
