package com.interview.practice.jee.atm;

import com.interview.practice.jee.atm.model.card.CardEntity;
import com.interview.practice.jee.atm.model.user.UserEntity;

import java.math.BigDecimal;

public class CardInfoDto {
    private Long id;
    private Long cardNumber;
    private BigDecimal amount;
    private String userFirstName;
    private String userLastName;

    public CardInfoDto(CardEntity cardEntity, UserEntity userEntity) {
        this.id = cardEntity.getId();
        this.cardNumber = cardEntity.getCardNumber();
        this.amount = cardEntity.getAmount();
        this.userFirstName = userEntity.getFirstName();
        this.userLastName = userEntity.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
