package com.excilys.mapper;

import java.time.format.DateTimeFormatter;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;
import com.excilys.util.LocalDateTimeUtil;

public class ComputerMapperDTO implements MapperDTO<Computer, ComputerDTO> {

	@Override
	public Computer dtoToModel(ComputerDTO dto) {
		final Computer computer = new Computer();
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		final DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		if ((dto.getIntroduced() != null) && !dto.getIntroduced().isEmpty()) {
			String localDateTime = LocalDateTimeUtil.convertToValidLocalDateTime(introduced);
		}
		return computer;
	}

	@Override
	public ComputerDTO modelToDto(Computer model) {
		final ComputerDTO dto = new ComputerDTO();
		// TODO
		// dto.setId(model.getId());
		// dto.setName(model.getName());
		// final DateTimeFormatter formatter = DateTimeFormatter
		// .ofPattern("yyyy-MM-dd HH:mm:ss");
		// dto.setIntroduced(formatter.p);
		return dto;
	}

}
