package com.github.elkanuco.fund_transfer.exceptions;

public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 3315472971466287989L;
	public InvalidDataException(String message) {
        super(message);
    }
}
