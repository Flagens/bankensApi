package com.example.demo.service;

import com.example.demo.AuxilaryFunctions;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionDTO;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;
    private final AuxilaryFunctions auxilaryFunctions;

    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionRepository.findAllTransactionsForAccount(accountId);
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElse(null);
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

    public boolean existsById(Long id) {
        return transactionRepository.existsById(id);
    }

    public TransactionDTO mapEntityToDTO(Transaction entity) {
        return modelMapper.map(entity, TransactionDTO.class);
    }

    public Transaction mapDTOToEntity(TransactionDTO dto) {
        return modelMapper.map(dto, Transaction.class);
    }

    public List<TransactionDTO> getTransactionDTOsForAccount(Long accountId) {
        return getTransactionsForAccount(accountId).stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO getTransactionDTO(Long id) {
        Transaction transaction = getTransaction(id);
        if (transaction == null) {
            return null;
        }
        return mapEntityToDTO(transaction);
    }

    public Transaction createTransactionFromDTO(TransactionDTO transactionDTO) {
        if (!auxilaryFunctions.accountExists(transactionDTO.getSender_id(), transactionDTO.getReceiver_id())) {
            return null;
        }
        Transaction transaction = mapDTOToEntity(transactionDTO);
        transaction.setTimestamp(LocalDate.now());
        return addTransaction(transaction);
    }

    public TransactionDTO updateTransactionFromDTO(Long id, TransactionDTO transactionDTO) {
        if (!auxilaryFunctions.accountExists(transactionDTO.getSender_id(), transactionDTO.getReceiver_id())) {
            return null;
        }
        Transaction transaction = mapDTOToEntity(transactionDTO);
        transaction.setTransaction_id(id);
        Transaction updatedTransaction = updateTransaction(transaction);
        return mapEntityToDTO(updatedTransaction);
    }
}
