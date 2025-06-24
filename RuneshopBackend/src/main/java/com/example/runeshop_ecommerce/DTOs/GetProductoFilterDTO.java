package com.example.runeshop_ecommerce.DTOs;

import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductoFilterDTO {

    @JsonProperty("sexo")
    @Schema(description = "Sexo del producto")
    private String sexo;

    @JsonProperty("marca")
    @Schema(description = "Marca del producto")
    private List<Marca> marca;

    @JsonProperty("talle")
    @Schema(description = "Talle del producto")
    private List<Integer> talleNumero;

    @JsonProperty("tipoProducto")
    @Schema(description = "Tipo del producto (Remera, zapatilla, etc)")
    private List<TipoProducto> tipoProducto;

    @JsonProperty("modelo")
    @Schema(description = "Modelo del producto (nombre)")
    private String modelo;

    @JsonProperty("categoria")
    @Schema(description = "Categoria del producto (Urbano, running, etc)")
    private List<String> categoria;

    @JsonProperty("min")
    @Schema(description = "Precio minimo que saldria del producto")
    private Double min;

    @JsonProperty("max")
    @Schema(description = "Precio maximo que saldria del producto")
    private Double max;

}
