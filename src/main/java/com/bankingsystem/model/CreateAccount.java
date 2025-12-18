package com.bankingsystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class CreateAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false)
    private Long accountNumber;

    private String accountHolderName;
    private String accountType;
    private Double balance;
    private java.time.LocalDateTime createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
