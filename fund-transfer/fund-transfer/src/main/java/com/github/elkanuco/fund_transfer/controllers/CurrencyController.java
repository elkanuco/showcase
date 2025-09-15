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

import com.github.elkanuco.fund_transfer.dtos.CurrencyDTO;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.services.CurrencyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/basiccrud/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService service;

    @PostMapping
    public CurrencyDTO create(@RequestBody CurrencyDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public CurrencyDTO read(@PathVariable Long id) throws NoDataFoundMatchingId {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public CurrencyDTO update( @RequestBody CurrencyDTO dto) throws NoDataFoundMatchingId {
        return service.update( dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<CurrencyDTO> list() {
        return service.list();
    }
}
