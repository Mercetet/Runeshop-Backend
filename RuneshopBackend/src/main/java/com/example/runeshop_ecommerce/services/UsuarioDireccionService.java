package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.repositories.DireccionRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioDireccionRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDireccionService extends BaseService<UsuarioDireccion, Long> {

    private final UsuarioRepository usuarioRepository;
    private final DireccionRepository direccionRepository;
    private final UsuarioDireccionRepository usuarioDireccionRepository;

    public UsuarioDireccionService(UsuarioDireccionRepository usuarioDireccionRepository, UsuarioRepository usuarioRepository, DireccionRepository direccionRepository, UsuarioDireccionRepository usuarioDireccionRepository1) {
        super(usuarioDireccionRepository);
        this.usuarioRepository = usuarioRepository;
        this.direccionRepository = direccionRepository;
        this.usuarioDireccionRepository = usuarioDireccionRepository1;
    }

    @Transactional
    public List<Direccion> direcionesPorUsuario(Long usuarioId) {
        return usuarioDireccionRepository.getDireccionesPorUsuario(usuarioId);
    }

    @Transactional
    public void agregarDireccion(Long id, Direccion direccion) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontro el usuario"));
            direccionRepository.save(direccion);
            UsuarioDireccion relacion = UsuarioDireccion.builder()
                    .usuario(usuario)
                    .direccion(direccion)
                    .build();
            usuarioDireccionRepository.save(relacion);
    }
}
