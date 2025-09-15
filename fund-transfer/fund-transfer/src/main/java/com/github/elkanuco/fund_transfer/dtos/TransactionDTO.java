package com.github.elkanuco.fund_transfer.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.github.elkanuco.fund_transfer.enums.TransactionType;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long id;
    private Long account;
    private TransactionType type;
    private Long currency;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
