package com.example.runeshop_ecommerce.exception;

public class DataExistException extends ApiException{
	public DataExistException(String mensaje) {
		super(mensaje, "EXISTING_USER_INFORMATION");
	}
}
