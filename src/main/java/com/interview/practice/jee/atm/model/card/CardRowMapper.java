package com.interview.practice.jee.atm.model.card;

import com.interview.practice.jee.atm.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRowMapper implements RowMapper<CardEntity> {
    @Override
    public List<CardEntity> map(ResultSet resultSet) throws SQLException {
        List<CardEntity> result = new ArrayList<>();
        while (resultSet.next()) {
            CardEntity cardEntity = new CardEntity();
            cardEntity.setId(resultSet.getLong("id"));
            cardEntity.setCardNumber(resultSet.getLong("card_number"));
            cardEntity.setPin(resultSet.getInt("pin"));
            cardEntity.setAmount(resultSet.getBigDecimal("amount"));
            cardEntity.setUserId(resultSet.getLong("user_id"));
            result.add(cardEntity);
        }
        return result;
    }
}
