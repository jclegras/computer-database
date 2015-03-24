package com.excilys.main;

import com.excilys.model.Computer;
import com.excilys.persistence.dao.ComputerDAO;
import com.excilys.util.SimplePage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Computer> computers = ComputerDAO.INSTANCE.getAll(new SimplePage(58, 10));
        for (Computer c : computers) {
            System.out.println(c);
        }
        System.out.println("size ---> " + computers.size());
    }
}
