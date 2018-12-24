package com.interview.practice.jee.atm.model.user;

import com.interview.practice.jee.atm.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<UserEntity> {
    @Override
    public List<UserEntity> map(ResultSet resultSet) throws SQLException {
        List<UserEntity> result = new ArrayList<>();
        while (resultSet.next()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(resultSet.getLong("id"));
            userEntity.setFirstName(resultSet.getString("firstname"));
            userEntity.setLastName(resultSet.getString("lastname"));
            result.add(userEntity);
        }
        return result;
    }
}
