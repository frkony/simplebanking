package com.eteration.simplebanking.model;


import org.springframework.util.Assert;

// This class is a place holder you can change the complete implementation
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }

    public static InsufficientBalanceException missingBalance() {
        return new InsufficientBalanceException("Insufficient funds to withdraw the relevant amount.");
    }
}
