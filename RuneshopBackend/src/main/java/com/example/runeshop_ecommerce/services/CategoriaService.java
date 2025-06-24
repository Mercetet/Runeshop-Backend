package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Categoria;
import com.example.runeshop_ecommerce.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends BaseService<Categoria, Long> {

    public CategoriaService(CategoriaRepository categoriaRepository) {
        super(categoriaRepository);
    }
}
