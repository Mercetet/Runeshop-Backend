package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Categoria;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "Categorias del producto")
public class CategoriaController extends BaseController<Categoria, Long> {

    public CategoriaController(CategoriaService categoriaService) {
        super(categoriaService);
    }
}
