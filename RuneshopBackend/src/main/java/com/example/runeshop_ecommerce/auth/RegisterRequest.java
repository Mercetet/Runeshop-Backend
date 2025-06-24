package com.example.runeshop_ecommerce.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String nombreUsuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
}
