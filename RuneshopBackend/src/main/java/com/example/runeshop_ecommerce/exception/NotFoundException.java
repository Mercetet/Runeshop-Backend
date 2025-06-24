package com.example.runeshop_ecommerce.exception;

public class NotFoundException extends ApiException {

    public NotFoundException(String mensaje) {
        super(mensaje, "ENTITY_NOT_FOUND");
    }
}
