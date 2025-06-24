package com.example.runeshop_ecommerce.utils;

import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class LimpiarDescuentosVencidos {

    private final DetalleRepository detalleRepository;

    public LimpiarDescuentosVencidos(DetalleRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void limpiarDescuentos() {
        LocalDateTime ahora = LocalDateTime.now();
        List<Detalle> detalles = detalleRepository.findAll();

        detalles.forEach(detalle -> {
            if ((detalle.getDescuentos() != null) && (ahora.isAfter(detalle.getFinDescuento()))) {
                detalle.setPrecioDescuento(null);
                detalle.setInicioDescuento(null);
                detalle.setFinDescuento(null);
                detalle.setDescuentos(null);
                detalleRepository.save(detalle);
            }
        });
    }

}
