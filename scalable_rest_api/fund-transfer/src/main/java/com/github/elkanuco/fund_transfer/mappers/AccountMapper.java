package com.github.elkanuco.fund_transfer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.elkanuco.fund_transfer.dtos.AccountDTO;
import com.github.elkanuco.fund_transfer.entities.Account;
import com.github.elkanuco.fund_transfer.entities.Bank;
import com.github.elkanuco.fund_transfer.entities.Currency;
import com.github.elkanuco.fund_transfer.entities.Subject;

import jakarta.persistence.EntityManager;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

	@Autowired
	protected EntityManager entityManager;

	@Mapping(target = "owner", source = "owner.id")
	@Mapping(target = "baseCurrency", source = "baseCurrency.id")
	@Mapping(target = "bank", source = "bank.id")
	public abstract AccountDTO toDto(Account entity);

	@Mapping(target = "owner", source = "owner", qualifiedByName = "idToSubject")
	@Mapping(target = "baseCurrency", source = "baseCurrency", qualifiedByName = "idToCurrency")
	@Mapping(target = "bank", source = "bank", qualifiedByName = "idToBank")
	public abstract Account toEntity(AccountDTO dto);

	@Named("idToSubject")
	protected  Subject idToSubject(Long id) {
		if (id == null)
			return null;
		return entityManager.getReference(Subject.class, id);
	}

	@Named("idToCurrency")
	protected  Currency idToCurrency(Long id) {
		if (id == null)
			return null;
		return entityManager.getReference(Currency.class, id);
	}

	@Named("idToBank")
	protected  Bank idToBank(Long id) {
		if (id == null)
			return null;
		return entityManager.getReference(Bank.class, id);
	}
}
