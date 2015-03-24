package com.excilys.main;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;


public class Main {
    public static void main(String[] args) {
//        List<Computer> computers = ComputerDAO.INSTANCE.getAll(new SimplePage(58, 10));
//        for (Computer c : computers) {
//            System.out.println(c);
//        }
//        System.out.println("size ---> " + computers.size());
    	Company company = CompanyService.INSTANCE.getById(2L);
    	Computer computer = new Computer();
    	computer.setName("name");
    	computer.setCompany(company);
    	ComputerService.INSTANCE.create(computer);
    }
}
