package com.github.elkanuco.fund_transfer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.dtos.TransactionDTO;
import com.github.elkanuco.fund_transfer.entities.Transaction;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.mappers.TransactionMapper;
import com.github.elkanuco.fund_transfer.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService implements CrudService<TransactionDTO, Long> {
	private final TransactionRepository repo;
	private final TransactionMapper mapper;

	@Override
	public TransactionDTO create(TransactionDTO dto) {
		Transaction entity = mapper.toEntity(dto);
		return mapper.toDto(repo.save(entity));
	}

	@Override
	public TransactionDTO findById(Long id) throws NoDataFoundMatchingId {
		return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NoDataFoundMatchingId("Transaction not found"));
	}

	@Override
	public TransactionDTO update(TransactionDTO dto) throws NoDataFoundMatchingId {
		Transaction entity = repo.findById(dto.getId()).orElseThrow(() -> new NoDataFoundMatchingId("Transaction not found"));
		Transaction updated = mapper.toEntity(dto);
		return mapper.toDto(repo.save(updated));
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<TransactionDTO> list() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

}
