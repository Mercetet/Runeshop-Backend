package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Direccion")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "localidad", "departamento", "provincia", "pais" })
public class Direccion extends Base{

    @JsonProperty("direccion")
    @NotNull(message = "La direccion no puede ser nulo")
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @JsonProperty("departamento")
    @NotNull(message = "El departamento no puede ser nulo")
    @Column(name = "departamento", nullable = false)
    private String departamento;

    @JsonProperty("provincia")
    @NotNull(message = "La provincia no puede ser nulo")
    @Column(name = "provincia",nullable = false)
    private String provincia;

    @JsonProperty("codigoPostal")
    @NotNull(message = "El codigo postal no puede ser nulo")
    @Column(name = "CP")
    private Integer codigoPostal;

    @JsonProperty("pais")
    @NotNull(message = "El pais no puede ser nulo")
    @Column(name = "pais", nullable = false)
    private String pais;

    @OneToMany(mappedBy = "direccion")
    @JsonIgnoreProperties("direccion")
    private List<UsuarioDireccion> usuariosDirecciones;
}
