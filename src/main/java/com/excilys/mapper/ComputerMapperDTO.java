package com.excilys.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class ComputerMapperDTO implements MapperDTO<Computer, ComputerDTO> {

	@Autowired
	private LocalDateTimeConverter localDateTimeConverter;

	@Override
	public Computer dtoToModel(ComputerDTO dto) {
		final Computer computer = new Computer();
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		if (dto.getIntroduced() != null) {
			dto.setIntroduced(dto.getIntroduced().trim());
			if (!dto.getIntroduced().isEmpty()) {
				computer.setIntroduced(localDateTimeConverter
						.convertToValidLocalDateTime(dto.getIntroduced()));
			}
		}
		if (dto.getDiscontinued() != null) {
			dto.setDiscontinued(dto.getDiscontinued().trim());
			if (!dto.getDiscontinued().isEmpty()) {
				computer.setDiscontinued(localDateTimeConverter
						.convertToValidLocalDateTime(dto.getDiscontinued()));
			}
		}
		if (dto.getCompanyId() != null) {
			dto.setCompanyId(dto.getCompanyId().trim());
			if (!dto.getCompanyId().isEmpty()
					&& dto.getCompanyId().matches("^[1-9][0-9]*$")) {
				final Company company = new Company();
				company.setId(Long.valueOf(dto.getCompanyId()));
				company.setName(dto.getCompanyName());
				computer.setCompany(company);
			}
		}

		return computer;
	}

	@Override
	public ComputerDTO modelToDto(Computer model) {
		final ComputerDTO dto = new ComputerDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		if (model.getIntroduced() != null) {
			dto.setIntroduced(localDateTimeConverter.convertToText(model
					.getIntroduced()));
		}
		if (model.getDiscontinued() != null) {
			dto.setDiscontinued(localDateTimeConverter.convertToText(model
					.getDiscontinued()));
		}
		if (model.getCompany() != null) {
			dto.setCompanyId(String.valueOf(model.getCompany().getId()));
			dto.setCompanyName(model.getCompany().getName());
		}
		return dto;
	}

}
