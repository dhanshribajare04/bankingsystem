// package com.bankingsystem.service;

// import org.springframework.stereotype.Service;
// import com.bankingsystem.model.CreateAccount;
// import com.bankingsystem.repository.AccountRepository;

// import java.time.LocalDateTime;

// @Service
// public class AccountService {

//     private final AccountRepository accountRepository;

//     public AccountService(AccountRepository accountRepository) {
//         this.accountRepository = accountRepository;
//     }

//     public CreateAccount createAccount(String name, String type) {

//         // Step 1: Create account without account number
//         CreateAccount account = new CreateAccount();
//         account.setAccountHolderName(name);
//         account.setAccountType(type);
//         account.setBalance(0.0);
//         account.setCreatedAt(LocalDateTime.now());

//         // Step 2: Save account to get DB-generated ID
//         CreateAccount savedAccount = accountRepository.save(account);

//         // Step 3: Generate account number using ID
//         long generatedAccountNumber = 1000000000L + savedAccount.getId();
//         savedAccount.setAccountNumber(generatedAccountNumber);

//         // Step 4: Save again with account number
//         return accountRepository.save(savedAccount);
//     }

//     public CreateAccount deposit(long accNumber, double amount) {

//         CreateAccount account = accountRepository.findByAccountNumber(accNumber)
//                 .orElseThrow(() -> new RuntimeException("Account not found"));

//         account.setBalance(account.getBalance() + amount);
//         return accountRepository.save(account);
//     }
// }


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


