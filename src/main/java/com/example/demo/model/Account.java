package com.example.demo.model;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Currency currency;


    //@OneToMany(mappedBy = "sender_id")
    //@JsonManagedReference

    @OneToMany
    @JoinColumn(name = "sender_id")
    private List<Transaction> transactions;


}
