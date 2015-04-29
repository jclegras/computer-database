package com.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Computer;
import com.excilys.service.IComputerService;

@RestController
@RequestMapping("/computer")
public class ComputerController {
	@Autowired
	private IComputerService computerService;
	@Autowired
	private MapperDTO<Computer, ComputerDTO> mapper;

	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<ComputerDTO> getAllComputers() {
		return mapper.modelsToDto(computerService.getAll());
	}
}
