package com.example.runeshop_ecommerce.exception;

public abstract class ApiException extends RuntimeException {
    private final String codigo;

    protected ApiException(String mensaje, String codigo) {
        super(mensaje);
        this.codigo = codigo;
    }

    public String getCodigoError() {
        return codigo;
    }
}
