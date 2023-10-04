package com.example.demo.model;

import com.example.demo.enums.transactionStatus;
import com.example.demo.enums.transactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transaction_id;
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "sender_id")
    //    @JsonBackReference
    private long sender_id;

    //    @ManyToOne
    //    @JoinColumn(name = "receiver_id")
    //    @JsonBackReference
    private long receiver_id;
    private BigDecimal amount;
    private LocalDate timestamp;
    @Enumerated(EnumType.STRING)
    private transactionType transaction_type;
    @Enumerated(EnumType.STRING)
    private transactionStatus transaction_status;




}
