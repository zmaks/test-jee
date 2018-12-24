package com.interview.practice.jee.atm.exception;

public class CardInsufficientFundsException extends Exception {
    private static final String DEFAULT_MESSAGE = "Cannot perform operation: Insufficient funds";

    public CardInsufficientFundsException() {
        this(DEFAULT_MESSAGE);
    }

    public CardInsufficientFundsException(String message) {
        super(message);
    }
}
