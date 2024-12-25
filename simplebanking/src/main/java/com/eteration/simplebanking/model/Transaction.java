package com.eteration.simplebanking.model;


import java.util.Date;

// This class is a place holder you can change the complete implementation
public abstract class Transaction {
	public Date date;
    public Double amount;
    public Transaction(Double dbl) {
        this.amount = dbl;
    }

    public Date getDate(){
        return this.date;
    }
    public Double getAmount(){
        return this.amount;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setAmount(Double amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                '}';
    }
}
