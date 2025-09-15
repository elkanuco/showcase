package com.github.elkanuco.fund_transfer.exceptions;

public class InsufficientBalanceException extends Exception {
	private static final long serialVersionUID = -1477641530523796131L;

	public InsufficientBalanceException(String message) {
        super(message);
    }

}
