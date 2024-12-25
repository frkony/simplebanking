package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation


import java.util.ArrayList;
import java.util.List;

public class Account {
    public String owner;
    public String accountNumber;
    public Double balance = 0.0;
    public List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void post(Transaction transaction) {
        credit(transaction.getAmount());
        getTransactions().add(transaction);
    }
    public void credit(Object temp) { // hesap Ödeme
        this.balance = this.balance + Double.parseDouble(temp.toString());
    }
    public void debit(Object temp) throws InsufficientBalanceException { // Borç Ödeme
        if (this.balance < (Double.parseDouble(temp.toString()) * -1))
            throw  InsufficientBalanceException.missingBalance();
        this.balance = this.balance - (Double.parseDouble(temp.toString()) * -1);
    }
    public void deposit(Object temp) { // Para yatırma
        this.balance = this.balance + Double.parseDouble(temp.toString());
    }

    public void withdraw(Object temp) throws InsufficientBalanceException { // Para çekme
        if (this.balance < Double.parseDouble(temp.toString()))
            throw  InsufficientBalanceException.missingBalance();

        this.balance = this.balance - Double.parseDouble(temp.toString());
    }

    public List<Transaction> getTransactions() {
        if (this.transactions == null) {
            this.transactions = new ArrayList<Transaction>();
        }
        return this.transactions;
    }

    public String getOwner(){
        return this.owner;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }
    public Double getBalance(){
        return this.balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "owner='" + owner + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions.toString() +
                '}';
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
