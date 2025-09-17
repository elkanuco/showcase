package com.github.elkanuco.fund_transfer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.elkanuco.fund_transfer.dtos.ExchangeRateResponseDto;

@FeignClient(name = "exchangeRateClient", url = "${exchangerate.client.url}")
public interface ExchangeRateClient {
	@GetMapping("/exchange-rate")
	ExchangeRateResponseDto getExchangeRate(@RequestParam("base") String base, @RequestParam("target") String target);
}
