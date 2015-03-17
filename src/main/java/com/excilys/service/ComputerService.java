package com.excilys.service;

import java.util.List;

import com.excilys.model.Computer;
import com.excilys.persistence.dao.ComputerDAO;
import com.excilys.util.Page;

public enum ComputerService {
	INSTANCE;

	public List<Computer> getAll() {
		return ComputerDAO.INSTANCE.getAll();
	}

	public List<Computer> getAll(Page page) {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		return ComputerDAO.INSTANCE.getAll(page);
	}

	public Computer getById(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		return ComputerDAO.INSTANCE.getById(id);
	}

	public void create(Computer computer) {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		ComputerDAO.INSTANCE.create(computer);
	}

	public void update(Computer computer) {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		ComputerDAO.INSTANCE.update(computer);
	}

	public void delete(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		ComputerDAO.INSTANCE.delete(id);
	}
}
