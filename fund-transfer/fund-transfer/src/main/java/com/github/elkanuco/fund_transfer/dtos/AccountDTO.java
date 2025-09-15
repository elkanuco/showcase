package com.github.elkanuco.fund_transfer.dtos;

import java.math.BigDecimal;

import com.github.elkanuco.fund_transfer.enums.AccountType;

import lombok.Data;

@Data
public class AccountDTO {
	private Long id;
	private Long owner;
	private Long baseCurrency;
	private Long bank;
	private String iban;
	private String name;
	private AccountType type;
	private BigDecimal balance;
}
