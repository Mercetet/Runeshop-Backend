package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.OrdenCompra;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.OrdenCompraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orden-compra")
@Tag(name = "Orden Compra", description = "Ordenes de compra de los usuarios")
public class OrdenCompraController extends BaseController<OrdenCompra, Long> {
    public OrdenCompraController(OrdenCompraService ordenCompraService) {
        super(ordenCompraService);
    }
}
