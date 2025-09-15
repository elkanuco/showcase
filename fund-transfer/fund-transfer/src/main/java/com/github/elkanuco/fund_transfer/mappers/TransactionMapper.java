package com.github.elkanuco.fund_transfer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.elkanuco.fund_transfer.dtos.TransactionDTO;
import com.github.elkanuco.fund_transfer.entities.Account;
import com.github.elkanuco.fund_transfer.entities.Currency;
import com.github.elkanuco.fund_transfer.entities.Transaction;

import jakarta.persistence.EntityManager;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {
	@Autowired
	protected EntityManager entityManager;

	@Mapping(target = "account", source = "account.id")
	@Mapping(target = "currency", source = "currency.id")
	public abstract TransactionDTO toDto(Transaction entity);

	@Mapping(target = "account", source = "account", qualifiedByName = "idToAccount")
	@Mapping(target = "currency", source = "currency", qualifiedByName = "idToCurrency")
	public abstract Transaction toEntity(TransactionDTO dto);
	
	
	@Named("idToAccount")
	protected  Account idToAccount(Long id) {
		if (id == null)
			return null;
		return entityManager.getReference(Account.class, id);
	}

	@Named("idToCurrency")
	protected  Currency idToCurrency(Long id) {
		if (id == null)
			return null;
		return entityManager.getReference(Currency.class, id);
	}
}
