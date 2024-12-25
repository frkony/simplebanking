package com.eteration.simplebanking.entity;

import com.eteration.simplebanking.model.Transaction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OWNER")
    public String owner;
    @Column(name = "ACCOUNT_NUMBER")
    public String accountNumber;
    @Column(name = "BALANCE")
    public Double balance;
    @Column(name = "CREATE_DATE")
    public Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
