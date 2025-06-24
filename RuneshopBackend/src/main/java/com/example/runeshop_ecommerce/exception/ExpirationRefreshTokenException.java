package com.example.runeshop_ecommerce.exception;

public class ExpirationRefreshTokenException extends ApiException{
	public ExpirationRefreshTokenException(String mensaje) {
		super(mensaje, "REFRESH_TOKEN_EXPIRATION");
	}
}
