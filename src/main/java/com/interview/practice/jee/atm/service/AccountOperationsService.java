package com.interview.practice.jee.atm.service;

import com.interview.practice.jee.atm.dao.DbConnectionUtil;
import com.interview.practice.jee.atm.exception.CardInsufficientFundsException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountOperationsService implements Service {
    private static final Logger LOGGER = Logger.getLogger(AccountOperationsService.class.getName());
    private static final String SELECT_FOR_UPDATE_QUERY_TEMPLATE = "select * from card where id=%d for update";

    public void changeAccountAmount(Long cardId, BigDecimal amount) throws SQLException, CardInsufficientFundsException {
        try (Connection connection = DbConnectionUtil.getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = String.format(SELECT_FOR_UPDATE_QUERY_TEMPLATE, cardId);
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    BigDecimal currentAmount = resultSet.getBigDecimal("amount");
                    BigDecimal newAmount = currentAmount.add(amount);
                    if (newAmount.compareTo(BigDecimal.ZERO) < 0) {
                        throw new CardInsufficientFundsException();
                    }
                    resultSet.updateBigDecimal("amount", newAmount);
                    resultSet.updateRow();
                } else {
                    throw new IllegalStateException("No card with id " + cardId);
                }
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
            connection.commit();
            LOGGER.log(Level.INFO, "Changed card amount. Card id: " + cardId + ", amount:" + amount);
        }
    }
}
