package com.gcash.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {

    private List<Account> accounts = new ArrayList<>();

    public Account createAccount(Double initialBalance) {
        String accountId = UUID.randomUUID().toString();

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);

        return account;
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public void addBalance(String accountId, Double amount) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                double newBalance = account.getBalance() + amount;
                account.setBalance(newBalance);
                return;
            }
        }
    }
}
