package com.codingbox.shop.exception;

// 예외를 custom해서 만듦
public class NotEnoughStockException extends RuntimeException {

	public NotEnoughStockException() {
		super();
	}

	public NotEnoughStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughStockException(String message) {
		super(message);
	}

	public NotEnoughStockException(Throwable cause) {
		super(cause);
	}

}
