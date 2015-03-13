package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
	T rowMap(ResultSet res) throws SQLException;
}
