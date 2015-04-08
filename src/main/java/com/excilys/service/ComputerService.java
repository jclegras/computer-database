package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.ComputerDAO;
import com.excilys.util.Page;

@Service
@Transactional(readOnly = true)
public class ComputerService implements IService<Computer, Long> {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerService.class);
    
    @Autowired
    private ComputerDAO computerDAO;

    public int count() {
        return computerDAO.count();
    }

    public List<Computer> getAll() {
        return computerDAO.getAll();
    }

    public List<Computer> getAll(Page page) {
        if (page == null) {
            throw new ServiceException(ExceptionMessage.ARG_NULL.toString());
        }
        return computerDAO.getAll(page);
    }

    public Computer getById(Long id) {
        if ((id == null) || (id <= 0)) {
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        return computerDAO.getById(id);
    }

    public List<Computer> getByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return computerDAO.getByName(name);
    }

    @Transactional
    public void create(Computer computer) {
        if (computer == null) {
            throw new ServiceException(ExceptionMessage.ARG_NULL.toString());
        }
        computerDAO.create(computer);
        LOGGER.info("Computer with id {} successfully created",
                computer.getId());
    }

    @Transactional
    public void update(Computer computer) {
        if (computer == null) {
            throw new ServiceException(ExceptionMessage.ARG_NULL.toString());
        }
        computerDAO.update(computer);
        LOGGER.info("Computer with id {} successfully created",
                computer.getId());
    }

    @Transactional
    public void delete(Long id) {
        if ((id != null) && (id <= 0)) {
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        computerDAO.delete(id);
        LOGGER.info("Computer with id {} successfully deleted", id);
    }
}
