package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Mapper for DTOs.
 *
 * @param <T> Model type
 * @param <U> Dto type
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
        System.out.println(models);
        return models.stream().map(this::modelToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param dto
     * @return The mapped model
     * @pre dto != null
     */
    T dtoToModel(U dto);

    /**
     * @param model
     * @return The mapped DTO
     * @pre model != null
     */
    U modelToDto(T model);
}
