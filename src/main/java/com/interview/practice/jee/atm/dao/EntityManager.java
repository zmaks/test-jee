package com.interview.practice.jee.atm.dao;

import java.sql.*;
import java.util.List;

public class EntityManager<E extends Entity> {

    private final RowMapper<E> rowMapper;

    public EntityManager(RowMapper<E> rowMapper) {
        this.rowMapper = rowMapper;
    }

    public Long save(String query, Object[] params) throws SQLException {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            int affectedRows = executeUpdate(ps, params);
            if (affectedRows == 0) {
                throw new SQLException("No rows affected during insert");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLException("No generated value returned during insert");
                }
            }
        }
    }

    public void update(String query, Object[] params) throws SQLException {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            executeUpdate(ps, params);
        }
    }

    public List<E> find(String query, Object[] params) throws SQLException {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                return rowMapper.map(rs);
            }
        }
    }

    public List<E> find(String query) throws SQLException {
        return find(query, new Object[]{});
    }

    public E findOne(String query, Object[] params) throws SQLException {
        List<E> listResult = find(query, params);
        if (listResult.size() > 1) throw new IllegalStateException("Query returned more than one result");
        if (listResult.isEmpty()) return null;
        return listResult.get(0);
    }

    public void delete(String query, Object[] params) throws SQLException {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            executeUpdate(ps, params);
        }
    }

    private int executeUpdate(PreparedStatement ps, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i+1, params[i]);
        }
        return ps.executeUpdate();
    }
}
