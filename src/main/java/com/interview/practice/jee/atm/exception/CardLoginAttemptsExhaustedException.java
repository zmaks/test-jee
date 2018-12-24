package com.interview.practice.jee.atm.exception;

public class CardLoginAttemptsExhaustedException extends Exception{
    private static final String DEFAULT_MESSAGE = "Login attempts are exhaust";
    public CardLoginAttemptsExhaustedException() {
        this(DEFAULT_MESSAGE);
    }

    public CardLoginAttemptsExhaustedException(String message) {
        super(message);
    }
}
