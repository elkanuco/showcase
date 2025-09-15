package com.github.elkanuco.fund_transfer.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BinaryOperator;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import com.github.elkanuco.fund_transfer.dtos.OperationDto;
import com.github.elkanuco.fund_transfer.entities.Account;
import com.github.elkanuco.fund_transfer.entities.Currency;
import com.github.elkanuco.fund_transfer.entities.Transaction;
import com.github.elkanuco.fund_transfer.enums.OperationType;
import com.github.elkanuco.fund_transfer.enums.TransactionType;
import com.github.elkanuco.fund_transfer.exceptions.InsufficientBalanceException;
import com.github.elkanuco.fund_transfer.exceptions.InvalidDataException;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;
import com.github.elkanuco.fund_transfer.repositories.AccountRepository;
import com.github.elkanuco.fund_transfer.repositories.CurrencyRepository;
import com.github.elkanuco.fund_transfer.repositories.TransactionRepository;

import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationsService {

	private final AccountRepository accountRepository;
	private final CurrencyRepository currencyRepository;
	private final TransactionRepository transactionRepository;
	private final CurrencyExchangeService currencyExchangeService;

	@Transactional
	public void process(OperationDto dto)
			throws InvalidDataException, NoDataFoundMatchingId, InsufficientBalanceException {

		// accountRepository.findByIdAndLockRowForUpdate -> @Lock(LockModeType.PESSIMISTIC_WRITE) -> SELECT ... FOR UPDATE in PostgreSQL
		Long baseAccountId = dto.getBaseAccountId();
		Account baseAccount = accountRepository.findByIdAndLockRowForUpdate(baseAccountId)//
				.orElseThrow(() -> new NoDataFoundMatchingId(String.format("Account not found: %s", baseAccountId)));//
		Long targetAccountId = dto.getTargetAccountId();
		Account targetAccount = accountRepository.findByIdAndLockRowForUpdate(targetAccountId).orElse(null);//

		OperationType operationType = dto.getType();
		String operationCurrencyCode = dto.getCurrency().trim();
		Currency operationCurrency = currencyRepository.findByCode(operationCurrencyCode)//
				.orElseThrow(() -> new NoDataFoundMatchingId(
						String.format("Currency not found: %s", operationCurrencyCode)));//
		BigDecimal baseExchangeRate = getExchangeRate(operationCurrencyCode, baseAccount.getBaseCurrency().getCode());
		BigDecimal operationAmount = dto.getAmount();
		BigDecimal operationAmountInBaseAccountCurrency = operationAmount.multiply(baseExchangeRate);
		BigDecimal currentBaseAccountBalance = baseAccount.getBalance();

		switch (operationType) {
		case DEPOSIT:
		case WITHDRAW:
			if (OperationType.WITHDRAW == operationType) {
				if (operationAmountInBaseAccountCurrency.compareTo(currentBaseAccountBalance) == 1) {
					throw new InsufficientBalanceException(
							String.format("Insuficient funds: %s", currentBaseAccountBalance));
				} else {
					saveTransaction(baseAccount, TransactionType.WITHDRAW, currentBaseAccountBalance,
							BigDecimal::subtract, operationAmount, operationAmountInBaseAccountCurrency,
							operationCurrency);
				}

			} else {
				saveTransaction(baseAccount, TransactionType.DEPOSIT, currentBaseAccountBalance, BigDecimal::add,
						operationAmount, operationAmountInBaseAccountCurrency, operationCurrency);
			}
			break;
		case TRANSFER:
			if (targetAccountId == null) {
				throw new InvalidDataException(String.format("Missing target account"));
			}
			if (targetAccount == null) {
				throw new NoDataFoundMatchingId(String.format("Account not found: %s", targetAccountId));
			}

			if (operationAmountInBaseAccountCurrency.compareTo(currentBaseAccountBalance) == 1) {
				throw new InsufficientBalanceException(
						String.format("Insuficient funds: %s", currentBaseAccountBalance));
			} else {
				saveTransaction(baseAccount, TransactionType.WITHDRAW, currentBaseAccountBalance, BigDecimal::subtract,
						operationAmount, operationAmountInBaseAccountCurrency, operationCurrency);
			}

			BigDecimal targetExchangeRate = getExchangeRate(operationCurrencyCode,
					targetAccount.getBaseCurrency().getCode());
			BigDecimal operationAmountInTargetAccountCurrency = operationAmount.multiply(targetExchangeRate);
			BigDecimal currentTargetAccountBalance = targetAccount.getBalance();
			saveTransaction(targetAccount, TransactionType.DEPOSIT, currentTargetAccountBalance, BigDecimal::add,
					operationAmount, operationAmountInTargetAccountCurrency, operationCurrency);

			break;
		default:
			throw new InvalidDataException("Unexpected operation type!");

		}
	}

	private void saveTransaction(Account account, TransactionType transactionType, BigDecimal currentBalance,
			BinaryOperator<BigDecimal> operation, BigDecimal operationAmount,
			BigDecimal operationAmountInAccountCurrency, Currency operationCurrency) {
		BigDecimal newBalance = operation.apply(currentBalance, operationAmountInAccountCurrency);
		account.setBalance(newBalance);
		accountRepository.save(account);
		Transaction transaction = new Transaction(account, TransactionType.WITHDRAW, operationAmount,
				operationCurrency);
		transactionRepository.save(transaction);
	}

	private BigDecimal getExchangeRate(String operationCurrency, String baseCurrencyCode) {
		return baseCurrencyCode.equals(operationCurrency) ? BigDecimal.ONE
				: currencyExchangeService.getExchangeRate(baseCurrencyCode, operationCurrency, LocalDate.now());
	}

}
