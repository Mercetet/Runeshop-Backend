package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Talle")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "numero" })
public class Talle extends Base{

    @JsonProperty("numero")
    @NotNull(message = "el numero del talle es nulo")
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @OneToMany(mappedBy = "talle")
    @JsonIgnoreProperties("talle")
    private List<Detalle> detalles;
}
