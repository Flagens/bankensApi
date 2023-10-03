package com.example.demo.model;

import com.example.demo.enums.transactionStatus;
import com.example.demo.enums.transactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long transaction_id;
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "sender_id")
    //    @JsonBackReference
    public long sender_id;

    //    @ManyToOne
    //    @JoinColumn(name = "receiver_id")
    //    @JsonBackReference
    public long receiver_id;
    public BigDecimal amount;
    public LocalDate timestamp;
    @Enumerated(EnumType.STRING)
    public transactionType transaction_type;
    @Enumerated(EnumType.STRING)
    public transactionStatus transaction_status;




}
