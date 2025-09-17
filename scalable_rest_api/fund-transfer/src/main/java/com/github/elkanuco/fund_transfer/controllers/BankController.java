package com.github.elkanuco.fund_transfer.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elkanuco.fund_transfer.dtos.BankDTO;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.services.BankService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/basiccrud/banks")
@RequiredArgsConstructor
public class BankController {

	private final BankService service;

	@PostMapping
	public BankDTO create(@RequestBody BankDTO dto) {
		return service.create(dto);
	}

	@GetMapping("/{id}")
	public BankDTO read(@PathVariable Long id) throws NoDataFoundMatchingId {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	public BankDTO update(@RequestBody BankDTO dto) throws NoDataFoundMatchingId {
		return service.update(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping
	public List<BankDTO> list() {
		return service.list();
	}
}
