package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.DTOs.CrearDetalleDTO;
import com.example.runeshop_ecommerce.entities.*;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.repositories.DescuentoRepository;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import com.example.runeshop_ecommerce.repositories.PrecioRepository;
import com.example.runeshop_ecommerce.repositories.TalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleService extends BaseService<Detalle, Long> {

    private final ImagenService imagenService;
    private final DetalleRepository detalleRepository;
    private final PrecioRepository precioRepository;
    private final TalleRepository talleRepository;
    private final DescuentoRepository descuentoRepository;

    public DetalleService(DetalleRepository detalleRepository, ImagenService imagenService, PrecioRepository precioRepository, TalleRepository talleRepository, DescuentoRepository descuentoRepository) {
        super(detalleRepository);
        this.detalleRepository = detalleRepository;
        this.imagenService = imagenService;
        this.precioRepository = precioRepository;
        this.talleRepository = talleRepository;
        this.descuentoRepository = descuentoRepository;
    }

    @Transactional
    public Page<Detalle> getDetallesPaginados(Pageable pageable) {
        return detalleRepository.getDetallesPaginados(pageable);
    }

    @Transactional
    public Detalle crearDetalle (
            List<MultipartFile> files,
            CrearDetalleDTO dto,
            Producto producto
    ) throws Exception {
        Precio precio = precioRepository.findById(dto.getPrecio().getId())
                .orElseThrow(() -> new NotFoundException("No se contro el id de ese precio"));

        Talle talle = talleRepository.findById(dto.getTalle().getId())
                .orElseThrow(() -> new NotFoundException("No se contro el id de ese talle"));

        Detalle detalle = Detalle.builder()
                .color(dto.getColor())
                .marca(dto.getMarca())
                .stock(dto.getStock())
                .talle(talle)
                .precio(precio)
                .inicioDescuento(dto.getInicioDescuento())
                .finDescuento(dto.getFinDescuento())
                .descuentos(dto.getDescuento())
                .build();
        detalle.setProducto(producto);
        precio.getDetalles().add(detalle);
        producto.getDetalles().add(detalle);
        talle.getDetalles().add(detalle);
        detalleRepository.save(detalle);

        if ( producto.getDetalles() == null || producto.getDetalles().isEmpty()) {
            producto.setDetalles(new ArrayList<>());
        }

        saveImagenInDetalle(detalle.getId(), files);

        return detalle;
    }

    @Transactional
    public Detalle saveImagenInDetalle(Long detalleId, List<MultipartFile> files) throws Exception {
        Detalle detalle = detalleRepository.findById(detalleId)
                .orElseThrow(() -> new NotFoundException("No se encontro el detalle"));

        if (files != null && !files.isEmpty()) {
            List<Imagen> imagenes = imagenService.subirImagen(files);
            agregarImagenAlDetalle(detalle, imagenes);
        } else {
            throw new Exception("Archivo a subir inexistente");
        }
        return detalleRepository.save(detalle);
    }

    public Detalle updateDetalle(Long detalleId, Long imagenId, MultipartFile file) throws Exception {
        Detalle detalle = detalleRepository.findById(detalleId)
                .orElseThrow(() -> new NotFoundException("Detalle no encontrado"));

        Imagen imagenAReemplazar = detalle.getImagenes()
                .stream()
                .filter(imagen -> imagen.getId().equals(imagenId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Imagen no encontrada"));

        Imagen subirImagen = imagenService.actualizarImagen(file);


        detalle.getImagenes().remove(imagenAReemplazar);
        detalle.getImagenes().add(subirImagen);
        imagenService.borrarImagen(imagenAReemplazar.getId());

        return detalleRepository.save(detalle);
    }

    public void agregarImagenAlDetalle(Detalle detalle, List<Imagen> imagenes) {
        if (detalle.getImagenes() == null || detalle.getImagenes().isEmpty()) {
            detalle.setImagenes(new ArrayList<>());
        }
        for (Imagen i : imagenes) {
            detalle.getImagenes().add(i);
        }
    }

    public Detalle aplicarDescuento(Long detalleId, Long descuentoId, LocalDateTime fechaFinal) {
        Detalle detalle = detalleRepository.findById(detalleId)
                .orElseThrow(() -> new NotFoundException("Detalle no encontrado"));

        Descuento descuento = descuentoRepository.findById(descuentoId)
                .orElseThrow(() -> new NotFoundException("Descuento no encontrado"));

        LocalDateTime fechaInicio = LocalDateTime.now();

        detalle.setDescuentos(descuento);

        Double precioOriginal = detalle.getPrecio().getPrecioVenta();
        Double valorDescuento = descuento.getValor();

        Double precioFinal = precioOriginal * (1 - valorDescuento);

        detalle.setPrecioDescuento(precioFinal);
        detalle.setInicioDescuento(fechaInicio);
        detalle.setFinDescuento(fechaFinal);
        descuento.getDetalles().add(detalle);

        return detalleRepository.save(detalle);
    }
}
