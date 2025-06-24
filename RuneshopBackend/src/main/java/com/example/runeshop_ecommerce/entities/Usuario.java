package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.Role;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({ "id", "nombre", "nombreUsuario", "apellido", "contraseña", "email", "tipoUsuario", "dni" })
public class Usuario extends Base implements UserDetails {

    @JsonProperty("nombre")
    @NotNull(message = "El nombre no puede ser nulo")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @JsonProperty("nombreUsuario")
    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @JsonProperty("apellido")
    @NotNull(message = "El apellido no puede ser nulo")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @JsonProperty("contraseña")
    @NotNull(message = "La contraseña no puede ser nulo")
    @Column(name = "contraseña", nullable = false)
    private String contrasenia;

    @JsonProperty("email")
    @NotNull(message = "El email no puede ser nulo")
    @Column(name = "email", nullable = false)
    private String email;

    @JsonProperty("tipoUsuario")
    @NotNull(message = "El rol de usuario no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Role tipoUsuario;

    @JsonProperty("dni")
    @Column(name = "DNI")
    private Integer dni;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<UsuarioDireccion> usuariosDirecciones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(tipoUsuario.name()));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    public boolean isCredentialNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
