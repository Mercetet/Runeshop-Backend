package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario_direccion")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioDireccion extends Base {

    @ManyToOne
    @JsonIgnoreProperties("usuariosDirecciones")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JsonIgnoreProperties("usuariosDirecciones")
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("usuariosDirecciones")
    @JoinColumn(name = "oredenes_de_compra")
    private List<OrdenCompra> ordenCompras;
}
