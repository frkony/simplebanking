package com.eteration.simplebanking.model;


import java.util.Date;

// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction {
    public DepositTransaction(Object amount) {
        super(Double.parseDouble(amount.toString()));
        setDate(new Date());
    }
}
