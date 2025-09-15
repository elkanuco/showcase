package com.github.elkanuco.fund_transfer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.github.elkanuco.fund_transfer.entities.Account;

import jakarta.persistence.LockModeType;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> { 
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "SELECT a FROM Account a WHERE a.id = :id")
	Optional<Account> findByIdAndLockRowForUpdate(Long id);
	
}
