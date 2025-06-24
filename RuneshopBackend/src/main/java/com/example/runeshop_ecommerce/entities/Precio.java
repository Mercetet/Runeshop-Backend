package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Precio")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "precioCompra", "precioVenta" })
public class Precio extends Base {

    @JsonProperty("precioCompra")
    @NotNull(message = "El precio de compra no puede ser nulo")
    @Column(name = "precio_compra", nullable = false)
    private Double precioCompra;

    @JsonProperty("precioVenta")
    @NotNull(message = "El precio de venta no puede ser nulo")
    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta;

    @OneToMany(mappedBy = "precio")
    @JsonIgnoreProperties("precio")
    private List<Detalle> detalles;


}
