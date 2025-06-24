package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Producto")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "modelo", "sexo", "tipoProducto"})
public class Producto extends Base{

    @JsonProperty("modelo")
    @NotNull(message = "El modelo del producto no puede ser nulo")
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @JsonProperty("sexo")
    @NotNull(message = "El sexo del producto no puede ser nulo")
    @Column(name = "sexo", nullable = false)
    private String sexo;

    @JsonProperty("tipoProducto")
    @NotNull(message = "El tipo de producto no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    @ManyToOne
    @JsonIgnoreProperties("productos")
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    @JsonIgnoreProperties("producto")
    private List<Detalle> detalles;

}
