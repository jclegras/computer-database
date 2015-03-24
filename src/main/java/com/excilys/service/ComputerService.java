package com.excilys.service;

import java.util.List;

import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.ComputerDAO;
import com.excilys.util.Page;

public enum ComputerService {
    INSTANCE;

    public int count() {
        return ComputerDAO.INSTANCE.count();
    }

    public List<Computer> getAll() {
        return ComputerDAO.INSTANCE.getAll();
    }

    public List<Computer> getAll(Page page) {
        if (page == null) {
            throw new ServiceException();
        }
        return ComputerDAO.INSTANCE.getAll(page);
    }

    public Computer getById(long id) {
        if (id <= 0) {
            throw new ServiceException();
        }
        return ComputerDAO.INSTANCE.getById(id);
    }

    public void create(Computer computer) {
        if (computer == null) {
            throw new ServiceException();
        }
        ComputerDAO.INSTANCE.create(computer);
    }

    public void update(Computer computer) {
        if (computer == null) {
            throw new ServiceException();
        }
        ComputerDAO.INSTANCE.update(computer);
    }

    public void delete(long id) {
        if (id <= 0) {
            throw new ServiceException();
        }
        ComputerDAO.INSTANCE.delete(id);
    }
}
