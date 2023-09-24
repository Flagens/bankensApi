package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.model.Transaction;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    public static final Long EMPTY_ID = null;
    public static final List<Transaction> EMPTY_TRANSACTION = null;
    private final AccountService accountService;

    @GetMapping("/a/")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/aa/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {

        if(accountService.existsById(id)) {
            Account account = accountService.getAccount(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/b/")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.createAccount(new Account(
                EMPTY_ID,
                accountDTO.getAccount_number(),
                accountDTO.getBalance(),
                accountDTO.getOwner_name(),
                accountDTO.getAccount_type(),
                accountDTO.getOpening_date(),
                accountDTO.is_blocked(),
                accountDTO.getCurrency(),
                EMPTY_TRANSACTION
        ));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(account);
    }

    @PutMapping("/bb/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        if(accountService.existsById(id)) {
            Account account = accountService.updateAccount(new Account(
                    id,
                    accountDTO.getAccount_number(),
                    accountDTO.getBalance(),
                    accountDTO.getOwner_name(),
                    accountDTO.getAccount_type(),
                    accountDTO.getOpening_date(),
                    accountDTO.is_blocked(),
                    accountDTO.getCurrency(),
                    accountDTO.getTransactions()
            ));
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(account);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/c/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        try {
            if (accountService.existsById(id)) {
                accountService.deleteAccount(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}

