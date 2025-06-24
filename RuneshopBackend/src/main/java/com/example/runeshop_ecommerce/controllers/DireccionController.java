package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.DireccionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
@Tag(name = "Direccion", description = "Direcciones de los usuarios")
public class DireccionController extends BaseController<Direccion, Long> {

    public DireccionController(DireccionService direccionService) {
        super(direccionService);
    }

}
