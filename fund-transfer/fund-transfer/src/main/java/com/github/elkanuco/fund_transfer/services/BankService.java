package com.github.elkanuco.fund_transfer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.dtos.BankDTO;
import com.github.elkanuco.fund_transfer.entities.Bank;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.mappers.BankMapper;
import com.github.elkanuco.fund_transfer.repositories.BankRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankService implements CrudService<BankDTO, Long> {
	private final BankRepository repo;
	private final BankMapper mapper;

	@Override
	public BankDTO create(BankDTO dto) {
		Bank entity = mapper.toEntity(dto);
		return mapper.toDto(repo.save(entity));
	}

	@Override
	public BankDTO findById(Long id) throws NoDataFoundMatchingId {
		return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NoDataFoundMatchingId("Bank not found"));
	}

	@Override
	public BankDTO update(BankDTO dto) throws NoDataFoundMatchingId {
		Bank entity = repo.findById(dto.getId()).orElseThrow(() -> new NoDataFoundMatchingId("Bank not found"));
		Bank updated = mapper.toEntity(dto);
		return mapper.toDto(repo.save(updated));
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<BankDTO> list() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

}
