package com.github.elkanuco.fund_transfer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elkanuco.fund_transfer.concurrency.DistributedLockService;
import com.github.elkanuco.fund_transfer.dtos.OperationDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationsController {
	private final DistributedLockService service;
	
	@PostMapping
	public ResponseEntity<Void> process(@Valid @RequestBody OperationDto dto) throws Exception {
		service.processWithMultipleLocks(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
