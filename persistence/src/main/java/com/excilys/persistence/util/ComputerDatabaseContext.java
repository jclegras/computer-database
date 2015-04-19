package com.excilys.persistence.util;

import com.excilys.model.Company;
import com.excilys.model.Computer;

import java.util.List;

public class ComputerDatabaseContext {
    private List<Computer> computers;
    private List<Company> companies;
    private Computer newComputer;
    private long computerId;
    private ComputerDatabaseScanner scanner;
    private String help;


    public ComputerDatabaseScanner getScanner() {
        return scanner;
    }

    public void setScanner(ComputerDatabaseScanner scanner) {
        this.scanner = scanner;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public long getComputerId() {
        return computerId;
    }

    public void setComputerId(long computerId) {
        this.computerId = computerId;
    }

    public Computer getNewComputer() {
        return newComputer;
    }

    public void setNewComputer(Computer newComputer) {
        this.newComputer = newComputer;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

}
