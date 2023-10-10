package com.example.demo.service;


import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionDTO;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionRepository.findAllTransactionsForAccount(accountId);
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElseThrow();
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);

    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }


    public boolean existsById(Long id) {return transactionRepository.existsById(id);}

    public TransactionDTO mapEntityToDTO(Transaction entity) {
        return modelMapper.map(entity, TransactionDTO.class);
    }

    public Transaction mapDTOToEntity(TransactionDTO dto) {
        return modelMapper.map(dto, Transaction.class);
    }
}
