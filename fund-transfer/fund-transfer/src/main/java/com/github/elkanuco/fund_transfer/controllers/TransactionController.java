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

import com.github.elkanuco.fund_transfer.dtos.TransactionDTO;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.services.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/basiccrud/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService service;

	@PostMapping
	public TransactionDTO create(@RequestBody TransactionDTO dto) {
		return service.create(dto);
	}

	@GetMapping("/{id}")
	public TransactionDTO read(@PathVariable Long id) throws NoDataFoundMatchingId {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	public TransactionDTO update(@RequestBody TransactionDTO dto) throws NoDataFoundMatchingId {
		return service.update(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping
	public List<TransactionDTO> list() {
		return service.list();
	}
}
