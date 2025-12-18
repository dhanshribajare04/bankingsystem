package com.bankingsystem.controller;


import com.bankingsystem.model.CreateAccount;
import com.bankingsystem.service.AccountService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/bank")
public class AccountController {

    private final AccountService accountService ;

    public AccountController(AccountService accountService){
        this.accountService = accountService ;
    }


    @PostMapping("/create")
    public CreateAccount createAccount(@RequestParam String name , @RequestParam String type){
        return accountService.createAccount(name, type) ;
    }
    
    @PostMapping("/deposit")
    public CreateAccount deposit(@RequestParam Long  accNumber , @RequestParam Double amount){
        return accountService.deposit(accNumber, amount);

    }

    @PostMapping("/withdraw")
    public CreateAccount withdraw(@RequestParam Long  accNumber , @RequestParam Double amount){
        return accountService.withdraw(accNumber, amount);

    }

    @GetMapping("/balance/{accNumber}")
    public ResponseEntity<Double> getBalance(@PathVariable Long accNumber){
        CreateAccount account = accountService.getAccountDetails(accNumber);
        return ResponseEntity.ok(account.getBalance());
    }

    


}
