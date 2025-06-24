package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Detalle")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "color", "estado", "marca", "stock", "descuentos", "precio_descuento" , "inicioDescuento", "finDescuento"})
public class Detalle extends Base{

    @JsonProperty("color")
    @NotNull(message = "El color del producto no puede ser nulo")
    @Column(name = "color", nullable = false)
    private String color;

    @JsonProperty("marca")
    @NotNull(message = "La marca del producto no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "marca", nullable = false)
    private Marca marca;

    @JsonProperty("stock")
    @NotNull(message = "El stock del producto no puede ser nulo")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @JsonProperty("precio_descuento")
    @Column(name = "precio_descuento")
    private Double precioDescuento;

    @ManyToOne
    @JsonIgnoreProperties("detalles")
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JsonIgnoreProperties("detalles")
    @JoinColumn(name = "talle_id", nullable = false)
    private Talle talle;

    @ManyToOne
    @JsonIgnoreProperties("detalles")
    @JoinColumn(name = "precio_id", nullable = false)
    private Precio precio;

    @ManyToMany
    @JsonIgnoreProperties("detalles")
    @JoinTable(
            name = "detalle_imagen",
            joinColumns = @JoinColumn(name = "detalle_id"),
            inverseJoinColumns = @JoinColumn(name = "imagen_id")
    )
    private List<Imagen> imagenes;

    @ManyToMany
    @JsonIgnoreProperties("detalles")
    private List<OrdenCompra> ordenCompras;

    @JsonProperty("inicioDescuento")
    @Column(name = "inicio_descuento")
    private LocalDateTime inicioDescuento;

    @JsonProperty("finDescuento")
    @Column(name = "fin_descuento")
    private LocalDateTime finDescuento;

    @ManyToOne
    @JsonProperty("descuentos")
    @JsonIgnoreProperties("detalles")
    private Descuento descuentos = Descuento.builder()
            .porcentaje("Sin descuento")
            .valor(0.0)
            .build();
}
