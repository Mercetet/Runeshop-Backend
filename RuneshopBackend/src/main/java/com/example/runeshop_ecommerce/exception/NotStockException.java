package com.example.runeshop_ecommerce.exception;

public class NotStockException extends ApiException{
	public NotStockException(String mensaje) {
		super(mensaje, "NO_STOCK_AVAILABLE");
	}
}
