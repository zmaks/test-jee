package com.interview.practice.jee.atm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<T extends Entity> {
    List<T> map(ResultSet resultSet) throws SQLException;
}
