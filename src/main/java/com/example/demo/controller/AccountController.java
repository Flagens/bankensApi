package com.example.demo.controller;

import com.example.demo.AuxilaryFunctions;
import com.example.demo.model.*;

import com.example.demo.service.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final AuxilaryFunctions auxilaryFunctions;



    @GetMapping("/accounts")
    public List<AccountDTO> getAccountDTOs() {
        List<Account> accounts = accountService.getAccounts();
        return accounts.stream()
                .map(accountService::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);


        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        AccountDTO accountDTO = accountService.mapEntityToDTO(account);

        return ResponseEntity.accepted().body(accountDTO);
        //return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountDTO);
    }



    @PostMapping("/createAccounts")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
//        if (accountDTO == null || accountDTO.getAccount_number() == null || accountDTO.getOwner_name() == null) {
//            return ResponseEntity.badRequest().build();
//        }

        Account account = accountService.mapDTOToEntity(accountDTO);
        account.setAccount_number(auxilaryFunctions.ranomNumberAccount(10));
        LocalDate date = LocalDate.now();
        account.setOpening_date(date);
        account = accountService.createAccount(account);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(account);
    }

    @PutMapping("/updateAccounts/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        if (!accountService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Account account = accountService.mapDTOToEntity(accountDTO);
        account.setAccount_id(id);

        Account updatedAccount = accountService.updateAccount(account);

        AccountDTO updatedDTO = accountService.mapEntityToDTO(updatedAccount);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(updatedDTO);
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

