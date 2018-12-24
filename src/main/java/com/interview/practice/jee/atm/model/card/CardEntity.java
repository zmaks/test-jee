package com.interview.practice.jee.atm.model.card;

import com.interview.practice.jee.atm.dao.Entity;

import java.math.BigDecimal;

public class CardEntity extends Entity {
    private Long cardNumber;
    private Integer pin;
    private BigDecimal amount;
    private Long userId;

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", pin=" + pin +
                ", amount=" + amount +
                ", userId=" + userId +
                '}';
    }
}
