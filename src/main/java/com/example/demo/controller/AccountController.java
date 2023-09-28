package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.model.Transaction;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountController {

    public static final Long EMPTY_ID = null;
    public static final List<Transaction> EMPTY_TRANSACTION = null;
    private final AccountService accountService;


    @GetMapping("/a")
    public List<AccountDTO> getAccountDTOs() {
        List<Account> accounts = accountService.getAccounts();
        return accounts.stream()
                .map(accountService::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/aa/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        AccountDTO accountDTO = accountService.mapEntityToDTO(account);
        return ResponseEntity.accepted().body(accountDTO);
        //return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountDTO);
    }



    @PostMapping("/b/")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
//        if (accountDTO == null || accountDTO.getAccount_number() == null || accountDTO.getOwner_name() == null) {
//            return ResponseEntity.badRequest().build();
//        }

        Account account = accountService.mapDTOToEntity(accountDTO);
        account = accountService.createAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(account);
    }

//    @PutMapping("/bb/{id}")
//    public ResponseEntity<Object> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
//        if(accountService.existsById(id)) {
//            Account account = accountService.updateAccount(new Account(
//                    id,
//                    accountDTO.getAccount_number(),
//                    accountDTO.getBalance(),
//                    accountDTO.getOwner_name(),
//                    accountDTO.getAccount_type(),
//                    accountDTO.getOpening_date(),
//                    accountDTO.getCurrency(),
//                    accountDTO.getTransactions()
//            ));
//            return ResponseEntity.status(HttpStatus.ACCEPTED)
//                    .body(account);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }

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

