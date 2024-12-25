package com.eteration.simplebanking.services;

import com.eteration.simplebanking.entity.BankAccountEntity;
import com.eteration.simplebanking.entity.TransactionsEntity;
import com.eteration.simplebanking.model.BankAccount;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.BankAccountRepository;
import com.eteration.simplebanking.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountApiService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    public BankAccount findAccount(String accountNumber) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByAccountNumber(accountNumber);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountEntity.getId());
        bankAccount.setAccountNumber(bankAccountEntity.getAccountNumber());
        bankAccount.setOwner(bankAccountEntity.getOwner());
        bankAccount.setBalance(bankAccountEntity.getBalance());
        bankAccount.setCreateDate(bankAccountEntity.getCreateDate());
        return bankAccount;
    }

    public Map<String, Object> findAccountDetail(String accountNumber) {
        Map<String, Object> responseMap = new HashMap<>();
        BankAccount bankAccount = findAccount(accountNumber);
        responseMap.put("accountNumber", bankAccount.getAccountNumber());
        responseMap.put("owner", bankAccount.getOwner());
        responseMap.put("balance", bankAccount.getBalance());
        responseMap.put("createDate", bankAccount.getCreateDate());

        List<TransactionsEntity> transactionsEntities =  transactionsRepository.findByAccountId(bankAccount.getId());
        List<Object> transactionMap = new ArrayList<>();
        for (int i = 0; i < transactionsEntities.size(); i++) {
            Map<String , Object> obj = new HashMap<>();
            obj.put("date", transactionsEntities.get(i).getDate());
            obj.put("amount", transactionsEntities.get(i).getAmount());
            obj.put("type",transactionsEntities.get(i).getType());
            obj.put("approvalCode", transactionsEntities.get(i).getApprovalCode());
            transactionMap.add(obj);

        }
        responseMap.put("transactions",transactionMap);

        return responseMap;
    }
}
