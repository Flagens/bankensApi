package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionDTO;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsForAccount(@PathVariable Long accountId) {
        List<TransactionDTO> transactionDTOs = transactionService.getTransactionDTOsForAccount(accountId);
        if (transactionDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transactionDTOs);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {
        TransactionDTO transactionDTO = transactionService.getTransactionDTO(id);
        if (transactionDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(transactionDTO);
    }

    @PostMapping("/createTransactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionService.createTransactionFromDTO(transactionDTO);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @PutMapping("/updateTransactions/{id}")
    public ResponseEntity<Object> updateTransaction(@PathVariable long id, @RequestBody TransactionDTO transactionDTO) {
        if (!transactionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        TransactionDTO updatedDTO = transactionService.updateTransactionFromDTO(id, transactionDTO);
        if (updatedDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDTO);
    }

    @DeleteMapping("/deleteTransactions/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable long id) {
        if (!transactionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
