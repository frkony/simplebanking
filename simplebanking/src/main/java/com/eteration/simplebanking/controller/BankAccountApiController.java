package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.BankAccountApiService;
import com.eteration.simplebanking.transactionProcess.TransactionProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/account/v1")
public class BankAccountApiController {

    @Autowired
    BankAccountApiService accountService;
    @Autowired
    TransactionProcessor transactionProcessor;
    @GetMapping(path = "/{str}")
    public ResponseEntity<Account> getAccount(@PathVariable String str) {
        Map<String, Object> account = accountService.findAccountDetail(str);
        ResponseEntity<Account> accountResponseEntity = new ResponseEntity(account, HttpStatus.OK);
        return accountResponseEntity;
    }


    @PostMapping(path = "/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody String json) throws JsonProcessingException {
        return transactionProcessor.credit(accountNumber, json);
    }
    @PostMapping(path = "/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody String json) throws InsufficientBalanceException, JsonProcessingException {
        return transactionProcessor.debit(accountNumber, json);
    }
}
