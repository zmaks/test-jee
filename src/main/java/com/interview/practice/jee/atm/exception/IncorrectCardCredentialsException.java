package com.interview.practice.jee.atm.exception;

public class IncorrectCardCredentialsException extends Exception {
    private static final String DEFAULT_MESSAGE = "Incorrect card credentials";

    public IncorrectCardCredentialsException() {
        this(DEFAULT_MESSAGE);
    }

    public IncorrectCardCredentialsException(String message) {
        super(message);
    }
}
