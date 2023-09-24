package com.example.demo.model;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.Currency;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    private String account_number;
    private BigDecimal balance;
    private String owner_name;
    @Enumerated(EnumType.STRING)
    private AccountType account_type;
    private LocalDate opening_date;
    private boolean is_blocked;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(mappedBy = "sender_id")
    @JsonManagedReference
    private List<Transaction> transactions;


}
