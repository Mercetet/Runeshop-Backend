package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orden_compra")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "total", "fechaCompra" })
public class OrdenCompra extends Base{

    @JsonProperty("total")
    @NotNull(message = "el total no puede ser nulo")
    @Column(name = "total", nullable = false)
    private Double total;

    @JsonProperty("fechaCompra")
    @NotNull(message = "la fecha de compra no puede ser nulo")
    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    @ManyToOne
    @JsonIgnoreProperties("ordenCompras")
    @JoinColumn(name = "id_usuario_direccion")
    private UsuarioDireccion usuarioDireccion;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnoreProperties("ordenCompras")
    @JoinTable(
            name = "ordenCompra_detalle",
            joinColumns = @JoinColumn(name = "ordeCompra_id"),
            inverseJoinColumns = @JoinColumn(name = "detalle_id")
    )
    private List<Detalle> detalles;
}
