package com.github.elkanuco.fund_transfer.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.elkanuco.fund_transfer.clients.ExchangeRateClient;
import com.github.elkanuco.fund_transfer.dtos.ExchangeRateResponseDto;

import feign.FeignException;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeServiceTest {

	@Mock
	private ExchangeRateClient exchangeRateClient;

	@InjectMocks
	private CurrencyExchangeService instance;

	@Test
	void getExchangeRate_happyPath() {
		// given
		String baseCurrency = "EUR";
		String targetCurrency = "USD";
		ExchangeRateResponseDto response = mock(ExchangeRateResponseDto.class);
		when(exchangeRateClient.getExchangeRate(baseCurrency, targetCurrency)).thenReturn(response);
		when(response.getRate()).thenReturn(0.123d);
		// when
		BigDecimal result = instance.getExchangeRate(baseCurrency, targetCurrency, LocalDate.now());
		// then
		assertThat(result).usingComparator(BigDecimal::compareTo).isEqualTo(BigDecimal.valueOf(0.123d));
	}

	@Test
	void getExchangeRate_serviceError() {
		// given
		String baseCurrency = "EUR";
		String targetCurrency = "USD";
		when(exchangeRateClient.getExchangeRate(baseCurrency, targetCurrency)).thenThrow(FeignException.class);
		// when - then
		assertThrows(FeignException.class,
				() -> instance.getExchangeRate(baseCurrency, targetCurrency, LocalDate.now()));
	}

}
