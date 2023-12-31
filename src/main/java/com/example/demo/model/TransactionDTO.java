package com.example.demo.model;

import com.example.demo.enums.transactionStatus;
import com.example.demo.enums.transactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class TransactionDTO {

    private long sender_id;
    private long receiver_id;
    private BigDecimal amount;
    //private LocalDate timestamp;
    private transactionType transaction_type;
    private transactionStatus transaction_status;

}
