package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByNombreUsuario(String username);
    Optional<Usuario> findUsuarioByEmail(String email);
    Optional<Usuario> findUsuarioByDni(Integer dni);
}
