package com.bankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bankingsystem.model.CreateAccount;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<CreateAccount, Long> {
    Optional<CreateAccount> findByAccountNumber(Long accountNumber);



}
