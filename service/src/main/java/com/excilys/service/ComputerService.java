package com.excilys.service;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.IComputerDAO;
import com.excilys.persistence.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComputerService implements IComputerService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerService.class);
    
    @Autowired
    private IComputerDAO computerDAO;

    @Transactional(readOnly = true)
    public Long count() {
        return computerDAO.count();
    }

    @Transactional(readOnly = true)
    public Long count(String search) {
        return computerDAO.count(search);
    }

    @Transactional(readOnly = true)
    public List<Computer> getAll() {
        return computerDAO.getAll();
    }

    @Transactional(readOnly = true)
    public List<Computer> getAll(Page page) {
        if (page == null) {
            LOGGER.error("Page is null");
            throw new ServiceException(ExceptionMessage.ARG_NULL.toString());
        }
        return computerDAO.getAll(page);
    }

    @Transactional(readOnly = true)
    public Computer getById(Long id) {
        if ((id == null) || (id <= 0)) {
            LOGGER.error("ID is invalid");
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        return computerDAO.getById(id);
    }

    @Transactional(readOnly = true)
    public List<Computer> getByName(Page page, String name) {
        if (name == null) {
            LOGGER.error("Name is null");
            throw new IllegalArgumentException();
        }
        return computerDAO.getByName(page, name);
    }

    public void create(Computer computer) {
        if (computer == null) {
            LOGGER.error("Computer is nul");
            throw new ServiceException(ExceptionMessage.ARG_NULL.toString());
        }
        computerDAO.create(computer);
        LOGGER.info("Computer with id {} successfully created",
                computer.getId());
    }

    public void update(Computer computer) {
        if (computer == null) {
            LOGGER.error("Computer is nul");
            throw new ServiceException(ExceptionMessage.ARG_NULL.toString());
        }
        computerDAO.update(computer);
        LOGGER.info("Computer with id {} successfully updated",
                computer.getId());
    }

    public void delete(Long id) {
        if ((id != null) && (id <= 0)) {
            LOGGER.error("ID is invalid");
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        computerDAO.delete(id);
        LOGGER.info("Computer with id {} successfully deleted", id);
    }
}
