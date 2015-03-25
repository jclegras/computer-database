package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Mapper for DTOs.
 * 
 * @param <T>
 *            Model type
 * @param <U>
 *            Dto type
 */
public interface MapperDTO<T, U> {
	/**
	 * @param models
	 * @return The mapped models
	 */
	default List<U> modelsToDto(List<T> models) {
		if (models == null) {
			throw new IllegalArgumentException();
		}
		return models.stream().map(m -> modelToDto(m))
				.collect(Collectors.toList());
	}
	
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
