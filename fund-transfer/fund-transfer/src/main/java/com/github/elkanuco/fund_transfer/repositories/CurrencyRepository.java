package com.github.elkanuco.fund_transfer.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.github.elkanuco.fund_transfer.entities.Currency;

@RepositoryRestResource
public interface CurrencyRepository extends JpaRepository<Currency, Long> { 
	
	Optional<Currency> findByCode(String code);
}
