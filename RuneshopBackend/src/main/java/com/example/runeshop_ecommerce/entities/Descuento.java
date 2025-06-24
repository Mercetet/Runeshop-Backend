package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Descuento")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "porcentaje", "valor" })
public class Descuento extends Base{

    @JsonProperty("porcentaje")
    @NotNull(message = "El porcentaje del descuento no puede ser nulo.")
    @Column(name = "porcentaje", nullable = false)
    private String porcentaje;

    @JsonProperty("valor")
    @NotNull(message = "El valor no puede ser nulo")
    @Column(name = "valor", nullable = false)
    private Double valor;

    @JsonIgnoreProperties("descuentos")
    @OneToMany(mappedBy = "descuentos")
    private List<Detalle> detalles;
}
