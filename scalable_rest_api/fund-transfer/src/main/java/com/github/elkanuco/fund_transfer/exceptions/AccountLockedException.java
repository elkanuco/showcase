package com.github.elkanuco.fund_transfer.exceptions;

public class AccountLockedException extends Exception {
	private static final long serialVersionUID = -1477641530523796131L;

	public AccountLockedException(String message) {
        super(message);
    }

}
