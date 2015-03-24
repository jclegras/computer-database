package com.excilys.mapper;

/**
 * Mapper for DTOs.
 * @param <T> Model type
 * @param <U> Dto type
 */
public interface MapperDTO<T, U> {
	/**
	 * @pre dto != null
	 * @param dto
	 * @return The mapped model
	 */
	T dtoToModel(U dto);
	/**
	 * @pre model != null
	 * @param model
	 * @return The mapped DTO
	 */
	U modelToDto(T model);
}
