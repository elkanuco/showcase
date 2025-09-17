package com.github.elkanuco.fund_transfer.handlers;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.elkanuco.fund_transfer.exceptions.AccountLockedException;
import com.github.elkanuco.fund_transfer.exceptions.InsufficientBalanceException;
import com.github.elkanuco.fund_transfer.exceptions.InvalidDataException;
import com.github.elkanuco.fund_transfer.exceptions.NoDataFoundMatchingId;

import feign.FeignException;
import feign.codec.DecodeException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<String> handleInsufficientBalance(InsufficientBalanceException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<String> handleInvalidData(InvalidDataException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}

	@ExceptionHandler(NoDataFoundMatchingId.class)
	public ResponseEntity<String> handleNoDataFound(NoDataFoundMatchingId ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Remote service error: " + ex.getMessage());
    }

    @ExceptionHandler(DecodeException.class)
    public ResponseEntity<String> handleDecodeException(DecodeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Invalid response from remote service: " + ex.getMessage());
    }
    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<String> handleAccountLockedException(AccountLockedException ex) {
    	return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
    			.body("Locked exception: " + ex.getMessage());
    }

    @ExceptionHandler(java.net.ConnectException.class)
    public ResponseEntity<String> handleConnectException(java.net.ConnectException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Unable to connect to remote service: " + ex.getMessage());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    	String errors = ex.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
    	return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
    			.body(errors);
    }

    @ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAll(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
