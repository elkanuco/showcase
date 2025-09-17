package com.github.elkanuco.fund_transfer.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.github.elkanuco.fund_transfer.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "account_id")
	private Account account;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransactionType type;

	@Column(nullable = false)
	private BigDecimal amount;

	@ManyToOne(optional = false)
	@JoinColumn(name = "currency_id")
	private Currency currency; // <-- newly added field

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	public Transaction(Account account, TransactionType type, BigDecimal amount, Currency currency) {
		super();
		this.account = account;
		this.type = type;
		this.amount = amount;
		this.currency = currency;
	}

	// Getters and setters by lombok
}
