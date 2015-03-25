package com.excilys.mapper;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

public enum CompanyMapperDTO implements MapperDTO<Company, CompanyDTO> {
	INSTANCE;

	@Override
	public Company dtoToModel(CompanyDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException();
		}
		final Company company = new Company();
		company.setId(dto.getId());
		company.setName(dto.getName());
		return company;
	}

	@Override
	public CompanyDTO modelToDto(Company model) {
		if (model == null) {
			throw new IllegalArgumentException();
		}
		final CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(model.getId());
		companyDTO.setName(model.getName());
		return companyDTO;
	}

}
