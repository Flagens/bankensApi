package com.example.demo.model;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class AccountDTO {
    private Long account_id;
    private String account_number;
    private BigDecimal balance;
    private String owner_name;
    private AccountType account_type;
    private LocalDate opening_date;
    private boolean is_blocked;
    private Currency currency;
    private List<Transaction> transactions;
}
