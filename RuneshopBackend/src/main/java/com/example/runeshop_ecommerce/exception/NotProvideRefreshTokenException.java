package com.example.runeshop_ecommerce.exception;

public class NotProvideRefreshTokenException extends ApiException{
	public NotProvideRefreshTokenException(String mensaje) {
		super(mensaje, "NO_REFRESH_TOKEN_PROVIDED");
	}
}
