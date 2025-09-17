package com.github.elkanuco.fund_transfer.mappers;

import org.mapstruct.Mapper;

import com.github.elkanuco.fund_transfer.dtos.BankDTO;
import com.github.elkanuco.fund_transfer.entities.Bank;


@Mapper(componentModel = "spring")
public interface BankMapper {
    BankDTO toDto(Bank bank);
    Bank toEntity(BankDTO dto);
}
