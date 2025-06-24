package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.repositories.TalleRepository;
import org.springframework.stereotype.Service;

@Service
public class TalleService extends BaseService<Talle, Long> {

    public TalleService(TalleRepository talleRepository) {
        super(talleRepository);
    }
}
