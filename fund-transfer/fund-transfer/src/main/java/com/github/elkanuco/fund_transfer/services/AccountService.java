package com.github.elkanuco.fund_transfer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.dtos.AccountDTO;
import com.github.elkanuco.fund_transfer.entities.Account;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.mappers.AccountMapper;
import com.github.elkanuco.fund_transfer.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements CrudService<AccountDTO, Long> {
	private final AccountRepository repo;
	private final AccountMapper mapper;

	@Override
	public AccountDTO create(AccountDTO dto) {
		Account entity = mapper.toEntity(dto);
		return mapper.toDto(repo.save(entity));
	}

	@Override
	public AccountDTO findById(Long id) throws NoDataFoundMatchingId {
		return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NoDataFoundMatchingId("Account not found"));
	}

	@Override
	public AccountDTO update(AccountDTO dto) throws NoDataFoundMatchingId {
		Account entity = repo.findById(dto.getId()).orElseThrow(() -> new NoDataFoundMatchingId("Account not found"));
		Account updated = mapper.toEntity(dto);
		return mapper.toDto(repo.save(updated));
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<AccountDTO> list() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

}
