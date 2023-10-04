package com.example.demo;

import com.example.demo.model.TransactionDTO;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuxilaryFunctions {



     public AccountService accountService;

    @Autowired
    public AuxilaryFunctions(AccountService accountService) {
        this.accountService = accountService;
    }

    public boolean accountExists(long sender_id, long receiver_id) {
        return (accountService.existsById(sender_id)) && (accountService.existsById(receiver_id));

    }



}
