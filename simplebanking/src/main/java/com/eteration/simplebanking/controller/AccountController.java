package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// This class is a place holder you can change the complete implementation
public class AccountController {

    @Autowired
    AccountService accountService;
    public ResponseEntity<Account> getAccount(String str) {
        Account account = accountService.findAccount(str);
        ResponseEntity<Account> accountResponseEntity = new ResponseEntity(account, HttpStatus.OK);
        return accountResponseEntity;
    }

    public ResponseEntity<TransactionStatus> credit(String accountNumber, DepositTransaction transaction) {
        if (transaction.getAmount() == null) {
            transaction.setAmount(0.0);
        }
        TransactionStatus transactionStatus = new TransactionStatus();
        Account account = accountService.findAccount(accountNumber);
        account.credit(transaction.getAmount());
        transactionStatus.setStatus("OK");
        transactionStatus.setApprovalCode("67f1aada-637d-4469-a650-3fb6352527ba");
        ResponseEntity<TransactionStatus> transactionStatusResponseEntity = new ResponseEntity(transactionStatus, HttpStatus.OK);
        return transactionStatusResponseEntity;
    }
    public ResponseEntity<TransactionStatus> debit(String accountNumber, WithdrawalTransaction transaction) throws InsufficientBalanceException {
        TransactionStatus transactionStatus = new TransactionStatus();
        Account account = accountService.findAccount(accountNumber);
        account.debit(transaction.getAmount());

        transactionStatus.setStatus("OK");
        transactionStatus.setApprovalCode("a66cce54-335b-4e46-9b49-05017c4b38dd");
        ResponseEntity<TransactionStatus> transactionStatusResponseEntity = new ResponseEntity(transactionStatus, HttpStatus.OK);
        return transactionStatusResponseEntity;
	}
}