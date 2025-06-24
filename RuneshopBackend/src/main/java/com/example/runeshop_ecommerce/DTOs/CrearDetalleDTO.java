package com.example.runeshop_ecommerce.DTOs;

import com.example.runeshop_ecommerce.entities.Descuento;
import com.example.runeshop_ecommerce.entities.Precio;
import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearDetalleDTO {

    @JsonProperty("color")
    private String color;

    @JsonProperty("estado")
    private boolean estado;

    @JsonProperty("marca")
    private Marca marca;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("talles")
    private Talle talle;

    @JsonProperty("precio")
    private Precio precio;

    @JsonProperty("descuento")
    private Descuento descuento;

    @JsonProperty("inicioDescuento")
    private LocalDateTime inicioDescuento;

    @JsonProperty("finDescuento")
    private LocalDateTime finDescuento;


}
