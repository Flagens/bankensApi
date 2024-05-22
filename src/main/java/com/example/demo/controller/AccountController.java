package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccountDTOs() {
        return accountService.getAllAccountDTOs();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.getAccountDTO(id);
        if (accountDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(accountDTO);
    }

    @PostMapping("/createAccounts")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.createAccountFromDTO(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping("/updateAccounts/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        if (!accountService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        AccountDTO updatedDTO = accountService.updateAccountFromDTO(id, accountDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDTO);
    }

    @DeleteMapping("/deleteAccounts/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        if (accountService.existsById(id)) {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
