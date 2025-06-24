package com.example.runeshop_ecommerce.DTOs;

import com.example.runeshop_ecommerce.entities.Precio;
import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearProductoDTO {
    @JsonProperty("categoriaId")
    private Long categoriaId;

    @JsonProperty("modelo")
    private String modelo;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("tipoProducto")
    private TipoProducto tipoProducto;

}

