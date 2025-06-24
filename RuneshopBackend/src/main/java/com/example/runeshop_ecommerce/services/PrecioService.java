package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Precio;
import com.example.runeshop_ecommerce.repositories.PrecioRepository;
import org.springframework.stereotype.Service;

@Service
public class PrecioService extends BaseService<Precio, Long> {

    public PrecioService(PrecioRepository precioRepository) {
        super(precioRepository);
    }
}
