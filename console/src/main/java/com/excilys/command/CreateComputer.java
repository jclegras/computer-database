package com.excilys.command;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;
import com.excilys.dto.ComputerDTO;

public class CreateComputer implements ICommand {

	@Override
	public void execute(IComputerDatabaseStorage store,
			IComputerDatabaseContext ctx) {
		ctx.setComputerDTO(new ComputerDTO());
		System.out.println("Name: ");
		if (ctx.getScanner().hasNextToken()) {
			ctx.getComputerDTO().setName(ctx.getScanner().getNextToken());
		}
		System.out.println("Introduced ? y/n");
		if (ctx.getScanner().hasNextToken()) {
			if (ctx.getScanner().getNextToken().equals("y")) {
				System.out.println("Introduced: ");
				if (ctx.getScanner().hasNextToken()) {
					ctx.getComputerDTO().setIntroduced(
							ctx.getScanner().getNextToken());
				}
			}
		}
		System.out.println("Discontinued ? y/n");
		if (ctx.getScanner().hasNextToken()) {
			if (ctx.getScanner().getNextToken().equals("y")) {
				System.out.println("Discontinued: ");
				if (ctx.getScanner().hasNextToken()) {
					ctx.getComputerDTO().setDiscontinued(
							ctx.getScanner().getNextToken());
				}
			}
		}
		System.out.println("Company's id ? y/n");
		if (ctx.getScanner().hasNextToken()) {
			System.out.println("Company's id: ");
			if (ctx.getScanner().getNextToken().equals("y")) {
				if (ctx.getScanner().hasNextToken()) {
					ctx.getComputerDTO().setCompanyId(
							ctx.getScanner().getNextToken());
				}
			}
		}
		ctx.getWebService().create(ctx.getComputerDTO());
		System.out.println("Successfull creation");
	}

}
