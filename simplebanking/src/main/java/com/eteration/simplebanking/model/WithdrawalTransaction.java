package com.eteration.simplebanking.model;


import java.util.Date;

// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(Object amount) {
        super((Double.parseDouble(amount.toString())) * -1);
        setDate(new Date());
    }
}


