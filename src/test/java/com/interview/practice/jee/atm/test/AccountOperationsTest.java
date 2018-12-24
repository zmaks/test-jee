package com.interview.practice.jee.atm.test;

import com.interview.practice.jee.atm.exception.CardInsufficientFundsException;
import com.interview.practice.jee.atm.model.card.CardDao;
import com.interview.practice.jee.atm.model.card.CardEntity;
import com.interview.practice.jee.atm.service.AccountOperationsService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AccountOperationsTest {

    private CardDao cardDao = new CardDao();
    private AccountOperationsService accountOperationsService = new AccountOperationsService();

    private List<Long> idsToDelete = new ArrayList<>();

    @After
    public void after() throws SQLException {
        for (Long id : idsToDelete) {
            cardDao.delete(id);
        }
    }

    @Test
    public void simpleTest() throws SQLException, CardInsufficientFundsException {
        BigDecimal startAmount = BigDecimal.valueOf(1000L);
        final Long id = createCard(startAmount);
        accountOperationsService.changeAccountAmount(id, BigDecimal.TEN);
        CardEntity cardEntity = cardDao.findById(id);
        Assert.assertEquals(cardEntity.getAmount(), startAmount.add(BigDecimal.TEN));
    }

    @Test
    public void concurrentTest() throws SQLException, CardInsufficientFundsException {

        BigDecimal startAmount = BigDecimal.valueOf(1000L);
        final Long cardId = createCard(startAmount);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        final int n = 20;

        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    accountOperationsService.changeAccountAmount(cardId, BigDecimal.valueOf(10L));
                } catch (SQLException | CardInsufficientFundsException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    accountOperationsService.changeAccountAmount(cardId, BigDecimal.valueOf(-10L));
                } catch (SQLException | CardInsufficientFundsException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CardEntity res = cardDao.findById(cardId);
        Assert.assertEquals(startAmount, res.getAmount());
    }

    @Test(expected = CardInsufficientFundsException.class)
    public void insufficientAmountTest() throws SQLException, CardInsufficientFundsException {
        BigDecimal amount = BigDecimal.TEN;
        final long cardId = createCard(amount);
        accountOperationsService.changeAccountAmount(cardId, BigDecimal.valueOf(-11L));
    }

    private Long createCard(BigDecimal amount) throws SQLException {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setAmount(amount);
        cardEntity.setCardNumber(1231234L);
        cardEntity.setPin(123);
        Long id = cardDao.save(cardEntity).getId();
        idsToDelete.add(id);
        return id;
    }

}
