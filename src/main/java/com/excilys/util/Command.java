package com.excilys.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.excilys.exception.ServiceException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

/**
 * Pattern command.
 */
public enum Command {
	/**
	 * Retrieve all computers.
	 */
	GET_ALL_COMPUTERS("getAllComputers") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			ctx.setComputers(ComputerService.INSTANCE.getAll());
			System.out.println(ctx.getComputers());
		}

	},
	/**
	 * Retrieve all companies.
	 */
	GET_ALL_COMPANIES("getAllCompanies") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			ctx.setCompanies(CompanyService.INSTANCE.getAll());
			System.out.println(ctx.getCompanies());
		}

	},
	/**
	 * Retrieve a computer.
	 */
	GET_BY_ID_COMPUTER("getByIdComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			ctx.setComputers(Arrays.asList(ComputerService.INSTANCE.getById(ctx
					.getComputerId())));
			System.out.println(ctx.getComputers());
		}

	},
	/**
	 * Create a new computer.
	 */
	CREATE_COMPUTER("createComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			ctx.setComputerId(ComputerService.INSTANCE.create(ctx
					.getNewComputer()));
			System.out.println(ctx.getComputers());
		}

	},
	/**
	 * Update a computer.
	 */
	UPDATE_COMPUTER("updateComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			ComputerService.INSTANCE.update(ctx.getNewComputer());
		}

	},
	/**
	 * Delete a computer.
	 */
	DELETE_COMPUTER("deleteComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			ComputerService.INSTANCE.delete(ctx.getComputerId());
		}

	};
	
	private static Map<String, Command> commands;
	static {
		commands = new HashMap<>();
		for (Command com : Command.values()) {
			commands.put(com.commandLabel, com);
		}
	}
	
	private final String commandLabel;
	
	private Command(String commandLabel) {
		this.commandLabel = commandLabel;
	}
	
	/**
	 * Return a command from its textual value.
	 * @param command Textual command
	 * @return The matching command
	 */
	public static Command getCommand(String command) {
		return commands.get(command);
	}

	public abstract void execute(ComputerDatabaseContext ctx)
			throws ServiceException;
}
