package com.example.demo.model;

import com.example.demo.enums.transactionStatus;
import com.example.demo.enums.transactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class TransactionDTO {

    public Account sender_id;
    public Account receiver_id;
    public BigDecimal amount;
    public LocalDate timestamp;
    public transactionType transaction_type;
    public transactionStatus transaction_status;

}
