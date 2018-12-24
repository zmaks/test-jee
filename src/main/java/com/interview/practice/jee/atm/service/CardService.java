package com.interview.practice.jee.atm.service;

import com.interview.practice.jee.atm.CardInfoDto;
import com.interview.practice.jee.atm.exception.CardLoginAttemptsExhaustedException;
import com.interview.practice.jee.atm.exception.IncorrectCardCredentialsException;
import com.interview.practice.jee.atm.model.card.CardDao;
import com.interview.practice.jee.atm.model.card.CardEntity;
import com.interview.practice.jee.atm.model.user.UserDao;
import com.interview.practice.jee.atm.model.user.UserEntity;

public class CardService implements Service{

    private static final int MAX_LOGIN_ATTEMPTS = 3;

    private final CardDao cardDao;
    private final UserDao userDao;

    public CardService(CardDao cardDao, UserDao userDao) {
        this.cardDao = cardDao;
        this.userDao = userDao;
    }

    public CardInfoDto getCardInfo(long cardNumber, int pin, int attemptNumber) throws Exception {
        if (attemptNumber >= MAX_LOGIN_ATTEMPTS) {
            throw new CardLoginAttemptsExhaustedException();
        }
        CardEntity cardEntity = cardDao.findByCardNumber(cardNumber);
        if (cardEntity == null || !cardEntity.getPin().equals(pin)) {
            throw new IncorrectCardCredentialsException();
        }

        UserEntity userEntity = userDao.findById(cardEntity.getUserId());
        return new CardInfoDto(cardEntity, userEntity);
    }

    /*public void addMoney(Long cardId, BigDecimal amount) throws Exception {
        Connection connection = DbConnectionUtil.getConnection();
        int isolation = connection.getTransactionIsolation();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            CardEntity cardEntity = cardDao.findById(cardId, connection);
            cardEntity.setAmount(cardEntity.getAmount().add(amount));
            cardDao.update(cardEntity, connection);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setTransactionIsolation(isolation);
            connection.setAutoCommit(true);
            connection.close();
        }


    }*/
}
