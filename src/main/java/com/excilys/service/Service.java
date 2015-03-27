package com.excilys.service;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T> The entity to manage.
 * @param <I> The id type.
 * @inv getAll() != null && 0 <= getAll().size()
 */
public interface Service<T, I extends Serializable> {
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
     * @param id Identifier
     * @return The matching entity
     * @pre id != null && id > 0
     */
    default T getById(I id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a new entity.
     *
     * @param entity
     * @pre entity != null
     */
    default void create(T entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Update the entity.
     *
     * @param entity Entity to update
     * @pre entity != null
     */
    default void update(T entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete a entity by its identifier.
     *
     * @param id Identifier
     * @pre id != null && id > 0
     */
    default void delete(I id) {
        throw new UnsupportedOperationException();
    }
}
