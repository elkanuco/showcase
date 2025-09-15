package com.github.elkanuco.fund_transfer.exceptions;

public class NoDataFoundMatchingId extends Exception {
	private static final long serialVersionUID = -4422678270767437137L;

	public NoDataFoundMatchingId(String message) {
		super(message);
	}
}
