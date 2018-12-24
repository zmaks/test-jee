package com.interview.practice.jee.atm.test;

import com.interview.practice.jee.atm.model.user.UserDao;
import com.interview.practice.jee.atm.model.user.UserEntity;
import com.interview.practice.jee.atm.model.user.UserRowMapper;
import org.junit.Test;

import java.sql.SQLException;

public class DbTest {

    @Test
    public void test() throws SQLException {
        UserRowMapper rowMapper = new UserRowMapper();
        UserDao userDao = new UserDao(rowMapper);
        UserEntity userEntity = userDao.save(new UserEntity("Bob", "Torr"));
        System.out.println("new user id = " + userEntity.getId());
        System.out.println(userDao.findAll());
    }
}
