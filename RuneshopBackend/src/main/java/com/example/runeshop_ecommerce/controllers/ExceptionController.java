package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.DTOs.ErrorResponse;

import com.example.runeshop_ecommerce.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse NotFoundHandler(NotFoundException ex) {
		return new ErrorResponse(ex.getCodigoError(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse ValidationErrorHandler(MethodArgumentNotValidException ex) {
		String mensaje = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.collect(Collectors.joining(", "));

		return new ErrorResponse("VALIDATION_ERROR", mensaje);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse MalformedJsonHandler(HttpMessageNotReadableException ex) {
		return new ErrorResponse("MALFORMED_JSON", "El cuerpo de la petición está mal formado o vacío.");
	}

	@ExceptionHandler(ExpirationRefreshTokenException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse ExpirationRefreshTokenHandler(ExpirationRefreshTokenException ex) {
		return new ErrorResponse(ex.getCodigoError(), ex.getMessage());
	}

	@ExceptionHandler(NotProvideRefreshTokenException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse NotProvideRefreshTokenHandler(NotProvideRefreshTokenException ex) {
		return new ErrorResponse(ex.getCodigoError(), ex.getMessage());
	}

	@ExceptionHandler(NotStockException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse NotStockHandler(NotStockException ex) {
		return new ErrorResponse(ex.getCodigoError(), ex.getMessage());
	}

	@ExceptionHandler(ExpirationAccessTokenException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse ExpirationAccessTokenHandler(ExpirationAccessTokenException ex) {
		return new ErrorResponse(ex.getCodigoError(), ex.getMessage());
	}

	@ExceptionHandler(DataExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse DataExistHandler(DataExistException ex) {
		return new ErrorResponse(ex.getCodigoError(), ex.getMessage());
	}
}
