package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Imagen")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "nombre", "imagenUrl" })
public class Imagen extends Base {

    @NotBlank
    private String nombre;

    @JsonProperty("imagenUrl")
//    @NotNull(message = "La URL no puede ser nulo")
//    @JoinColumn(name = "imagenUrl", nullable = false)
    private String imagenUrl;

    @ManyToMany(mappedBy = "imagenes")
    @JsonIgnoreProperties("imagenes")
    private List<Detalle> detalles;


    public Imagen(String nombre, String imagenUrl) {
        this.nombre = nombre;
        this.imagenUrl = imagenUrl;
    }
}
