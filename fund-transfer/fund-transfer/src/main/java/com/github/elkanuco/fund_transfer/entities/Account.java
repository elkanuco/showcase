package com.github.elkanuco.fund_transfer.entities;

import java.math.BigDecimal;
import java.util.List;

import com.github.elkanuco.fund_transfer.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private Subject owner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "base_currency_id")
    private Currency baseCurrency;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(nullable = true, unique = true)
    private String iban;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    // Getters and setters by lombok
}
