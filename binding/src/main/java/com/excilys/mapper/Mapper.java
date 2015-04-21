package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param <T> Entity type
 */
public interface Mapper<T> {
    /**
     * Maps result set into entity.
     *
     * @param res Result to map
     * @return The mapped entity
     * @throws SQLException
     * @pre res != null
     */
    T rowMap(ResultSet res) throws SQLException;
}