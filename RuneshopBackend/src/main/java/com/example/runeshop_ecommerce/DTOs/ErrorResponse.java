package com.example.runeshop_ecommerce.DTOs;

import lombok.Data;

@Data
public class ErrorResponse {
    private String codigo;
    private String mensaje;

    public ErrorResponse(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }


}
