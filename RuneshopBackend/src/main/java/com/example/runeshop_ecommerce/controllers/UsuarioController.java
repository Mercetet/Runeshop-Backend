package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Usuarios que realizan las compras")
public class UsuarioController extends BaseController<Usuario, Long> {

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
    }
}
