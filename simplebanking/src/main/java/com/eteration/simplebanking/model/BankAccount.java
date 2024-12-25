package com.eteration.simplebanking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    public Long id;
    public String owner;
    public String accountNumber;
    public Double balance = 0.0;
    public Date createDate;
    public List<Transaction> transactions;

    public BankAccount() {}
    public BankAccount(String owner, Object accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber.toString();
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

    public void post(Transaction transaction) {
        credit(transaction.getAmount());
        getTransactions().add(transaction);
    }
    public void credit(Object temp) { // hesap Ödeme
        this.balance = this.balance + Double.parseDouble(temp.toString());
    }
    public void debit(Object temp) { // Borç Ödeme
        this.balance = this.balance - Double.parseDouble(temp.toString());
    }
    public List<Transaction> getTransactions() {
        if (this.transactions == null) {
            this.transactions = new ArrayList<Transaction>();
        }
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
