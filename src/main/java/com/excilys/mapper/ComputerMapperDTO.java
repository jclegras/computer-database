package com.excilys.mapper;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum ComputerMapperDTO implements MapperDTO<Computer, ComputerDTO> {
    INSTANCE;

    @Override
    public Computer dtoToModel(ComputerDTO dto) {
        final Computer computer = new Computer();
        computer.setId(dto.getId());
        computer.setName(dto.getName());
        final DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(LocalDateTimeUtil.DEFAULT_PATTERN);
        if (dto.getIntroduced() != null) {
            dto.setIntroduced(dto.getIntroduced().trim());
            if (!dto.getIntroduced().isEmpty()) {
                dto.setIntroduced(LocalDateTimeUtil
                        .convertToValidLocalDateTime(dto.getIntroduced()));
                computer.setIntroduced(LocalDateTime.parse(dto.getIntroduced(),
                        formatter));
            }
        }
        if (dto.getDiscontinued() != null) {
            dto.setDiscontinued(dto.getDiscontinued().trim());
            if (!dto.getDiscontinued().isEmpty()) {
                dto.setDiscontinued(LocalDateTimeUtil
                        .convertToValidLocalDateTime(dto.getDiscontinued()));
                computer.setDiscontinued(LocalDateTime.parse(
                        dto.getDiscontinued(), formatter));
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
        final DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(LocalDateTimeUtil.DEFAULT_PATTERN);
        if (model.getIntroduced() != null) {
            dto.setIntroduced(model.getIntroduced().format(formatter));
        }
        if (model.getDiscontinued() != null) {
            dto.setDiscontinued(model.getDiscontinued().format(formatter));
        }
        if (model.getCompany() != null) {
            dto.setCompanyId(String.valueOf(model.getCompany().getId()));
            dto.setCompanyName(model.getCompany().getName());
        }
        return dto;
    }

}
