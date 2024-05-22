package com.example.demo.service;

import com.example.demo.AuxilaryFunctions;
import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final AuxilaryFunctions auxilaryFunctions;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public AccountDTO getAccountDTO(Long id) {
        Account account = getAccount(id);
        if (account == null) {
            return null;
        }
        return mapEntityToDTO(account);
    }

    public List<AccountDTO> getAllAccountDTOs() {
        return getAccounts().stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account createAccountFromDTO(AccountDTO accountDTO) {
        Account account = mapDTOToEntity(accountDTO);
        account.setAccount_number(auxilaryFunctions.ranomNumberAccount(10));
        account.setOpening_date(LocalDate.now());
        return createAccount(account);
    }

    public AccountDTO updateAccountFromDTO(Long id, AccountDTO accountDTO) {
        Account account = mapDTOToEntity(accountDTO);
        account.setAccount_id(id);
        Account updatedAccount = updateAccount(account);
        return mapEntityToDTO(updatedAccount);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return accountRepository.existsById(id);
    }

    public AccountDTO mapEntityToDTO(Account entity) {
        return modelMapper.map(entity, AccountDTO.class);
    }

    public Account mapDTOToEntity(AccountDTO dto) {
        return modelMapper.map(dto, Account.class);
    }
}
