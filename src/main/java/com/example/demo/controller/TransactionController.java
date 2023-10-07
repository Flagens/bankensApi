package com.example.demo.controller;

import com.example.demo.AuxilaryFunctions;
import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionDTO;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final AuxilaryFunctions auxilaryFunctions;



    @GetMapping("/transactions/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsForAccount(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionsForAccount(accountId);

        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(transactionService::mapEntityToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transactionDTOs);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionDTO> getAccount(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransaction(id);

        if (!(transactionService.existsById(id))) {
            return ResponseEntity.notFound().build();
        }

        TransactionDTO transactionDTO = transactionService.mapEntityToDTO(transaction);
        return ResponseEntity.accepted().body(transactionDTO);
        //return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountDTO);
    }

    @PostMapping("/createTransactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {


        if(!(auxilaryFunctions.accountExists(transactionDTO.getSender_id(), transactionDTO.getReceiver_id()))) {
            return ResponseEntity.notFound().build();
        }

            Transaction transaction = transactionService.mapDTOToEntity(transactionDTO);
            LocalDate date = LocalDate.now();
            transaction.setTimestamp(date);
            transaction = transactionService.addTransaction(transaction);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(transaction);

    }

    @PutMapping("/updateTransactions/{id}")
    public ResponseEntity<Object> updateTransaction(@PathVariable long id, @RequestBody TransactionDTO transactionDTO) {

        if(!(auxilaryFunctions.accountExists(transactionDTO.getSender_id(), transactionDTO.getReceiver_id()))) {
            return ResponseEntity.notFound().build();
        }

        Transaction transaction = transactionService.mapDTOToEntity(transactionDTO);
        transaction.setTransaction_id(id);

        Transaction updatedTransaction = transactionService.updateTransaction(transaction);

        TransactionDTO updatedDTO = transactionService.mapEntityToDTO(updatedTransaction);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDTO);


    }

    @DeleteMapping("deleteTransactions/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable long id) {

        if (transactionService.existsById(id)) {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }



}
