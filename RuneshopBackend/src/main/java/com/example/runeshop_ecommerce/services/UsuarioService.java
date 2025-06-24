package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario, Long> {

    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}
