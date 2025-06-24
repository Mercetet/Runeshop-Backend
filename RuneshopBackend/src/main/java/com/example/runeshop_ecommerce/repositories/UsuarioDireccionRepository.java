package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioDireccionRepository extends BaseRepository<UsuarioDireccion, Long> {

    @Query("SELECT ud.direccion FROM UsuarioDireccion ud WHERE ud.usuario.id = :usuarioId")
    List<Direccion> getDireccionesPorUsuario(
            @Param("usuarioId") Long usuarioId
    );
}
