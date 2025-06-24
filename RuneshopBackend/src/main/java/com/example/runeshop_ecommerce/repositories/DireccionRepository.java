package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Direccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends BaseRepository<Direccion, Long> {
}
