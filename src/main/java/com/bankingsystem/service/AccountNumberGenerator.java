package com.bankingsystem.service;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class AccountNumberGenerator {

    private final AtomicLong counter = new AtomicLong(1000000000L); // Starting from a 10-digit number

    public Long generateAccountNumber() {
        return counter.incrementAndGet();
    }


}
