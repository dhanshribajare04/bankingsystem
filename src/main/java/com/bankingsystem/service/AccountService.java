package com.bankingsystem.service;

import org.springframework.stereotype.Service;

import com.bankingsystem.model.CreateAccount;
import com.bankingsystem.repository.AccountRepository;

import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountNumberGenerator generator;

    public AccountService(AccountRepository accountRepository, AccountNumberGenerator generator) {
        this.accountRepository = accountRepository;
        this.generator = generator;
    }

    public  CreateAccount createAccount(String name, String type) {
        CreateAccount account = new CreateAccount();
        account.setAccountHolderName(name);
        account.setAccountType(type);
        account.setAccountNumber(generator.generateAccountNumber());
        account.setBalance(0.0);
        account.setCreatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    public CreateAccount deposit(long accNumber , double amount){
        CreateAccount account = accountRepository.findByAccountNumber(accNumber)
        .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);

    }

    public CreateAccount withdraw(long accNumber , double amount){
        CreateAccount account = accountRepository.findByAccountNumber(accNumber)
        .orElseThrow(() -> new RuntimeException("Account not found"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);

    }

    public CreateAccount getAccountDetails(long accNumber){
        return accountRepository.findByAccountNumber(accNumber)
        .orElseThrow(() -> new RuntimeException("Account not found"));
    }




}


