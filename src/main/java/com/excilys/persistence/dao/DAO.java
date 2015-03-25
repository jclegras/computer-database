package com.excilys.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T> The entity to manage.
 * @param <I> The id type.
 * @inv getAll() != null && 0 <= getAll().size()
 */
public interface DAO<T, I extends Serializable> {
    /**
     * Retrieve all entities.
     *
     * @return Entities
     */
    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieve entity by its identifier.
     * 
     * @pre id != null && id > 0
     * @param id Identifier
     * @return The matching entity
     */
    default T getById(I id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a new entity.
     *
     * @pre entity != null
     * @param entity
     */
    default void create(T entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Update the entity.
     *
     * @pre entity != null
     * @param entity Entity to update
     */
    default void update(T entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete a entity by its identifier.
     *
     * @pre id != null && id > 0
     * @param id Identifier
     */
    default void delete(I id) {
        throw new UnsupportedOperationException();
    }
}
