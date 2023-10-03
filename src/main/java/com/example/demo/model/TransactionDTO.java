package com.example.demo.model;

import com.example.demo.enums.transactionStatus;
import com.example.demo.enums.transactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO {

    private long sender_id;
    private long receiver_id;
    private BigDecimal amount;
    private LocalDate timestamp;
    private transactionType transaction_type;
    private transactionStatus transaction_status;

}
