package com.excilys.command;

import java.util.HashMap;

public enum CommandToken {
	COUNT("count", new Count()),
	CREATE_COMPUTER("createComputer", new CreateComputer()),
	DELETE_COMPANY("deleteCompany", new DeleteCompany()),
	DELETE_COMPUTER("deleteComputer", new DeleteComputer()),
	GET_ALL_COMPANIES("getAllCompanies", new GetAllCompanies()),
	GET_ALL_COMPUTERS("getAllComputers", new GetAllComputers()),
	GET_BY_ID_COMPANY("getByIdCompany", new GetByIdCompany()),
	GET_BY_ID_COMPUTER("getByIdComputer", new GetByIdComputer()),
	GET_BY_NAME("getByName", new GetByName()),
	UPDATE_COMPUTER("updateComputer", new UpdateComputer());
	
	private static final HashMap<String, ICommand> DATA;
	static {
		DATA = new HashMap<>();
		for (CommandToken c : CommandToken.values()) {
			DATA.put(c.name, c.command);
		}
	}
	
	private String name;
	private ICommand command;
	
	private CommandToken(String name, ICommand command) {
		this.name = name;
		this.command = command;
	}
	
	public static ICommand getCommand(String name) {
		final ICommand command = DATA.get(name);
		if (command == null) {
			throw new IllegalArgumentException();
		}
		return command;
	}
}
