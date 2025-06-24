package com.example.runeshop_ecommerce.exception;

public class ExpirationAccessTokenException extends ApiException{
	public ExpirationAccessTokenException(String mensaje) {
		super(mensaje, "ACCESS_TOKEN_EXPIRATION");
	}
}
