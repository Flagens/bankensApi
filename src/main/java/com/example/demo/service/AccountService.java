package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;



    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public boolean existsById(Long id) {return accountRepository.existsById(id);}

    public AccountDTO mapEntityToDTO(Account entity) {
        return modelMapper.map(entity, AccountDTO.class);
    }

    public Account mapDTOToEntity(AccountDTO dto) {
        return modelMapper.map(dto, Account.class);
    }


}
