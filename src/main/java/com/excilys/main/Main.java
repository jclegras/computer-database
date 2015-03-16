package com.excilys.main;

import java.time.LocalDateTime;

import com.excilys.dao.ComputerDAO;
import com.excilys.model.Computer;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println(ComputerDAO.INSTANCE.getById(7L));
		
		final Computer computer = new Computer();
		computer.setId(7891);
		computer.setName("myName2");
		computer.setIntroduced(LocalDateTime.now());
		computer.setDiscontinued(LocalDateTime.now());
		computer.setCompanyId(1L);
		
		System.out.println(ComputerDAO.INSTANCE.create(computer));
	}
}
