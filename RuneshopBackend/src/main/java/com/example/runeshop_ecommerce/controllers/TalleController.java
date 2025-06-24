package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.TalleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talle")
@Tag(name = "Talle", description = "Talles de los productos")
public class TalleController extends BaseController<Talle, Long> {

    public TalleController(TalleService talleService) {
        super(talleService);
    }
}
