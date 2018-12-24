package com.interview.practice.jee.atm.model.user;

import com.interview.practice.jee.atm.dao.EntityManager;
import com.interview.practice.jee.atm.dao.RowMapper;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private final EntityManager<UserEntity> entityManager;

    public UserDao(RowMapper<UserEntity> rowMapper) {
        this.entityManager = new EntityManager<>(rowMapper);
    }

    public UserDao() {
        this.entityManager = new EntityManager<>(new UserRowMapper());
    }

    public UserEntity save(UserEntity userEntity) throws SQLException {
        String query = "insert into user (firstname, lastname) values (?, ?)";
        Object[] params = new Object[] {userEntity.getFirstName(), userEntity.getLastName()};
        Long id = entityManager.save(query, params);
        userEntity.setId(id);
        return userEntity;
    }

    public List<UserEntity> findAll() throws SQLException {
        String query = "select * from user";
        return entityManager.find(query);
    }

    public UserEntity findById(Long id) throws SQLException {
        String query = "select * from user where id = ?";
        Object[] params = new Object[] {id};
        return entityManager.findOne(query, params);
    }
}
