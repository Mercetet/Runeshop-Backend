package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Descuento;
import com.example.runeshop_ecommerce.repositories.DescuentoRepository;
import org.springframework.stereotype.Service;

@Service
public class DescuentoService extends BaseService<Descuento, Long> {

    public DescuentoService(DescuentoRepository descuentoRepository) {
        super(descuentoRepository);
    }
}
