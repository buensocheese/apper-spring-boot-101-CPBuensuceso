package com.apper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final List<Account> accounts = new ArrayList<>();
    private final IdGeneratorService idGeneratorService;

    public AccountService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    public Account create(String firstName, String lastName, String username, String clearPassword) {
        Account account = new Account();

        String id = idGeneratorService.getNextId();
        System.out.println("Generated id: " + id);

        account.setId(id);
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        account.setVerificationCode(idGeneratorService.generateRandomCharacters(6));

        accounts.add(account);

        return account;
    }

    public Account get(String accountId) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getAll() {
        return accounts;
    }

    public Account update(String accountId, String firstName, String lastName, String email, String password) {
        Account account = get(accountId);
        if (account != null) {
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            account.setPassword(password);
            account.setLastUpdated(LocalDateTime.now());
        }
        return account;
    }

    public void delete(String accountId) {
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getId().equals(accountId)) {
                iterator.remove();
                break;
            }
        }
    }
}

