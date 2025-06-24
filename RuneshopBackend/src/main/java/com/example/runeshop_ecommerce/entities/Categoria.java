package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Categoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "nombre", "productos" })
public class Categoria extends Base {

    @JsonProperty("nombre")
    @NotNull(message = "El nombre de la categoría no puede ser nulo.")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @JsonIgnoreProperties("categoria")
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
