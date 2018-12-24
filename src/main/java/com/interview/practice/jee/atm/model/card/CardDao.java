package com.interview.practice.jee.atm.model.card;

import com.interview.practice.jee.atm.dao.EntityManager;

import java.sql.SQLException;
import java.util.List;

public class CardDao {

    private final EntityManager<CardEntity> entityManager;

    public CardDao(CardRowMapper rowMapper) {
        this.entityManager = new EntityManager<CardEntity>(rowMapper);
    }

    public CardDao() {
        this.entityManager = new EntityManager<CardEntity>(new CardRowMapper());
    }

    public CardEntity save(CardEntity cardEntity) throws SQLException {
        String query = "insert into card (card_number, pin, amount, user_id) values (?, ?, ?, ?)";
        Object[] params = new Object[] {cardEntity.getCardNumber(), cardEntity.getPin(), cardEntity.getAmount(), cardEntity.getUserId()};
        Long id = entityManager.save(query, params);
        cardEntity.setId(id);
        return cardEntity;
    }

    public List<CardEntity> findAll() throws SQLException {
        String query = "select * from card";
        return entityManager.find(query);
    }

    public CardEntity findByCardNumber(Long cardNumber) throws SQLException {
        String query = "select * from card where card_number = ?";
        Object[] params = new Object[] {cardNumber};
        return entityManager.findOne(query, params);
    }

    public CardEntity findById(Long id) throws SQLException {
        String query = "select * from card where id = ?";
        Object[] params = new Object[] {id};
        return entityManager.findOne(query, params);
    }

    public CardEntity update(CardEntity cardEntity) throws SQLException {
        String query = "update user set card_number=?, pin=? amount=?, user_id=? where id=?";
        Object[] params = new Object[] {cardEntity.getCardNumber(), cardEntity.getPin(), cardEntity.getAmount(), cardEntity.getUserId(), cardEntity.getId()};
        entityManager.update(query, params);
        return cardEntity;
    }

    public void delete(Long cardId) throws SQLException {
        String query = "delete from card where id=?";
        Object[] params = new Object[]{cardId};
        entityManager.delete(query, params);
    }
 }
