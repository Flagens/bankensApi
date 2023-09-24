package com.example.demo.model;

import com.example.demo.enums.transactionStatus;
import com.example.demo.enums.transactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long transaction_id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonBackReference
    public Account sender_id;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonBackReference
    public Account receiver_id;
    public BigDecimal amount;
    public LocalDate timestamp;
//    @Enumerated(EnumType.STRING)
//    public transactionType transaction_type;
//    @Enumerated(EnumType.STRING)
//    public transactionStatus transaction_status;


}
