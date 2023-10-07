package com.example.demo;

import com.example.demo.model.TransactionDTO;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Random;

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

    public String ranomNumberAccount(int length) {
        String characters ="0123456789";

        Random random = new Random();

        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();

    }



}
