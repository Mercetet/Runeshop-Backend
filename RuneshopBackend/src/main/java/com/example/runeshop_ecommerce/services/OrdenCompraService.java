package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.entities.OrdenCompra;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.exception.NotStockException;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import com.example.runeshop_ecommerce.repositories.OrdenCompraRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioDireccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenCompraService extends BaseService<OrdenCompra, Long> {

    private final DetalleRepository detalleRepository;
    private final UsuarioDireccionRepository usuarioDireccionRepository;
    private final OrdenCompraRepository ordenCompraRepository;


    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository, DetalleRepository detalleRepository, UsuarioDireccionRepository usuarioDireccionRepository, OrdenCompraRepository ordenCompraRepository1){
        super(ordenCompraRepository);
        this.detalleRepository = detalleRepository;
        this.usuarioDireccionRepository = usuarioDireccionRepository;
        this.ordenCompraRepository = ordenCompraRepository1;
    }

    @Transactional
    public OrdenCompra generarOrdenCompra(List<Long> detallesId, Long usuarioDireccionId) {
        List<Detalle> detalles = new ArrayList<>();
        Double precioTotal = 0.0;

        UsuarioDireccion usuarioDireccion = usuarioDireccionRepository.findById(usuarioDireccionId)
                .orElseThrow(() -> new NotFoundException("No se encontro el usuario"));

        for (Long l : detallesId) {
            Detalle d = detalleRepository.findById(l)
                    .orElseThrow(() -> new NotFoundException("No se encontro el detalle"));
            detalles.add(d);
        }

        if (usuarioDireccion.getOrdenCompras() == null) {
            usuarioDireccion.setOrdenCompras(new ArrayList<>());
        }


        for (Detalle d: detalles) {
            if (d.getDescuentos() != null) {
                precioTotal += d.getPrecioDescuento();
            } else {
                precioTotal += d.getPrecio().getPrecioVenta();
            }
        }

        OrdenCompra ordenCompra = OrdenCompra.builder()
                .total(precioTotal)
                .fechaCompra(LocalDateTime.now())
                .usuarioDireccion(usuarioDireccion)
                .detalles(detalles)
                .build();

        for (Detalle d : detalles) {
            d.getOrdenCompras().add(ordenCompra);
        }

        usuarioDireccion.getOrdenCompras().add(ordenCompra);

        return ordenCompraRepository.save(ordenCompra);
    }

    @Transactional
    public void reducirStock (Long id) {
        Detalle detalle = detalleRepository.findByIdAndUpdate(id);
        if (detalle.getStock() <= 0) {
            throw new NotStockException("No hay stock disponible");
        }

        detalle.setStock(detalle.getStock() - 1);
        detalleRepository.save(detalle);
    }

    @Transactional
    public void deleteOrdenCompraFallida(Long id) {
        OrdenCompra ordenCompra = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro la orden de compra"));

        UsuarioDireccion ud = ordenCompra.getUsuarioDireccion();

        ordenCompra.getDetalles().forEach(detalle -> detalle.setStock(detalle.getStock() + 1));
        ordenCompra.getDetalles().clear();

        ud.getOrdenCompras().remove(ordenCompra);

        usuarioDireccionRepository.save(ud);
    }
}
