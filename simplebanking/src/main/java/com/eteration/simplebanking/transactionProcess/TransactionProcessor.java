package com.eteration.simplebanking.transactionProcess;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.entity.TransactionsEntity;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.repository.TransactionsRepository;
import com.eteration.simplebanking.services.BankAccountApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class TransactionProcessor {
    @Autowired
    BankAccountApiService bankAccountApiService;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    public ResponseEntity<TransactionStatus> credit(String accountNumber, String json) throws JsonProcessingException {
        if (json == null) returnBadRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jNode = objectMapper.readTree(json);

        if (jNode == null) returnBadRequest();

        Double amount = jNode.get("amount").asDouble();
        if (amount == null || amount <= 0) returnBadRequest();
        BankAccount account = bankAccountApiService.findAccount(accountNumber);
        if (account == null) return returnBadRequest();

        account.credit(amount);
        TransactionStatus transactionStatus = update(account , amount,  "DepositTransaction");

        return new ResponseEntity(transactionStatus, HttpStatus.OK);
    }

    public ResponseEntity<TransactionStatus> debit(String accountNumber, String json) throws JsonProcessingException, InsufficientBalanceException {
        if (json == null) returnBadRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jNode = objectMapper.readTree(json);

        if (jNode == null) returnBadRequest();

        Double amount = jNode.get("amount").asDouble();

        if (amount == null || amount <= 0) returnBadRequest();
        if (bankAccountApiService == null) {
            bankAccountApiService = new BankAccountApiService();
        }
        BankAccount account = bankAccountApiService.findAccount(accountNumber);

        if (account == null) returnBadRequest();

        account.debit(amount);
        if (account.getBalance() < 0) {
            return new ResponseEntity("Insufficient funds to withdraw the relevant amount.", HttpStatus.OK);
        }
        TransactionStatus transactionStatus = update(account, amount, "WithdrawalTransaction");

        return new ResponseEntity(transactionStatus, HttpStatus.OK);
    }

    private TransactionStatus update(BankAccount account, Double amount, String type) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByAccountNumber(account.getAccountNumber());
        bankAccountEntity.setBalance(account.getBalance());
        bankAccountRepository.save(bankAccountEntity);

        TransactionsEntity transactionsEntity = new TransactionsEntity();
        transactionsEntity.setAmount(amount);
        transactionsEntity.setDate(new Date());
        transactionsEntity.setType(type);
        transactionsEntity.setAccountId(bankAccountEntity.getId());
        transactionsEntity.setApprovalCode(generateUUID());
        transactionsRepository.save(transactionsEntity);

        TransactionStatus transactionStatus = new TransactionStatus();
        transactionStatus.setStatus("OK");
        transactionStatus.setApprovalCode(transactionsEntity.getApprovalCode());
        return transactionStatus;
    }
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private ResponseEntity<TransactionStatus> returnBadRequest() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
