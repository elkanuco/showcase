package com.github.elkanuco.fund_transfer.dtos;

import java.math.BigDecimal;

import com.github.elkanuco.fund_transfer.enums.OperationType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationDto {
	@NotNull(message = "Operation type is mandatory")
	private OperationType type;
	@NotNull(message = "Base account id is mandatory")
	private Long baseAccountId;
	private Long targetAccountId;//nullable
	@NotNull(message = "Amount is mandatory")
	private BigDecimal amount;
	@NotNull(message = "Amount currenty is mandatory")
	private String currency;

}
