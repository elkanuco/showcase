package com.github.elkanuco.fund_transfer.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.clients.ExchangeRateClient;
import com.github.elkanuco.fund_transfer.dtos.ExchangeRateResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchangeService {
    private final ExchangeRateClient exchangeRateClient;


    /**
     * To avoid the http request whenever the rate for that day is known
     * 
     * @param baseCurrency
     * @param targetCurrency
     * @param date this should always be LocalDate.now() this is only hit the cache while the date does not change
     * @return the rate
     */
    @Cacheable(value = "exchangeRates", key = "T(java.lang.String).format('%s-%s-%s', #baseCurrency, #targetCurrency, #date.toString())")
    public BigDecimal getExchangeRate(String baseCurrency, String targetCurrency, LocalDate date /*ignored, for caching purposes only*/) {
        ExchangeRateResponseDto response = exchangeRateClient.getExchangeRate(baseCurrency, targetCurrency);
        log.info(String.format("Exchange rate service response: %s", response));
        return BigDecimal.valueOf(response.getRate());
    }
}

