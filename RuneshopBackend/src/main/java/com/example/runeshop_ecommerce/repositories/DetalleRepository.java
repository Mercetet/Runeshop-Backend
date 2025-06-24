package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Detalle;

import com.example.runeshop_ecommerce.entities.Producto;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends BaseRepository<Detalle, Long> {

    @Query("SELECT d FROM Detalle d ORDER BY d.producto.modelo ASC")
    Page<Detalle> getDetallesPaginados(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT d FROM Detalle d WHERE d.id = :id")
    Detalle findByIdAndUpdate(@Param("id") Long id);
}
