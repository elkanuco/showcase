package com.github.elkanuco.fund_transfer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.dtos.SubjectDTO;
import com.github.elkanuco.fund_transfer.entities.Subject;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.mappers.SubjectMapper;
import com.github.elkanuco.fund_transfer.repositories.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService implements CrudService<SubjectDTO, Long> {
	private final SubjectRepository repo;
	private final SubjectMapper mapper;

	@Override
	public SubjectDTO create(SubjectDTO dto) {
		Subject entity = mapper.toEntity(dto);
		return mapper.toDto(repo.save(entity));
	}

	@Override
	public SubjectDTO findById(Long id) throws NoDataFoundMatchingId {
		return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NoDataFoundMatchingId("Subject not found"));
	}

	@Override
	public SubjectDTO update(SubjectDTO dto) throws NoDataFoundMatchingId {
		Subject entity = repo.findById(dto.getId()).orElseThrow(() -> new NoDataFoundMatchingId("Subject not found"));
		Subject updated = mapper.toEntity(dto);
		return mapper.toDto(repo.save(updated));
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<SubjectDTO> list() {
		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

}
