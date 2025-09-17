package com.github.elkanuco.fund_transfer.mappers;


import org.mapstruct.Mapper;

import com.github.elkanuco.fund_transfer.dtos.CurrencyDTO;
import com.github.elkanuco.fund_transfer.entities.Currency;


@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDTO toDto(Currency currency);
    Currency toEntity(CurrencyDTO dto);
}
