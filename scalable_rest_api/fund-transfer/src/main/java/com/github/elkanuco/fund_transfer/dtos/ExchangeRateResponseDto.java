package com.github.elkanuco.fund_transfer.dtos;

import lombok.Data;

@Data
public class ExchangeRateResponseDto {
	private String base;
    private String target;
    private double rate;
}
