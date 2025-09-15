package com.github.elkanuco.fund_transfer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.dtos.CurrencyDTO;
import com.github.elkanuco.fund_transfer.entities.Currency;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.mappers.CurrencyMapper;
import com.github.elkanuco.fund_transfer.repositories.CurrencyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyService implements CrudService<CurrencyDTO, Long> {
	private final CurrencyRepository repo;
	private final CurrencyMapper mapper;

	@Override
	public CurrencyDTO create(CurrencyDTO dto) {
		Currency entity = mapper.toEntity(dto);
		return mapper.toDto(repo.save(entity));
	}

	@Override
	public CurrencyDTO findById(Long id) throws NoDataFoundMatchingId {
		return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NoDataFoundMatchingId("Currency not found"));
	}

	@Override
	public CurrencyDTO update(CurrencyDTO dto) throws NoDataFoundMatchingId {
		Currency entity = repo.findById(dto.getId()).orElseThrow(() -> new NoDataFoundMatchingId("Currency not found"));
		Currency updated = mapper.toEntity(dto);
		return mapper.toDto(repo.save(updated));
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<CurrencyDTO> list() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

}
