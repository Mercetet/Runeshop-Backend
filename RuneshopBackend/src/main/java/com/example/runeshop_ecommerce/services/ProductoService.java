package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.DTOs.CrearDetalleDTO;
import com.example.runeshop_ecommerce.DTOs.CrearProductoDTO;
import com.example.runeshop_ecommerce.entities.*;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.repositories.CategoriaRepository;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import com.example.runeshop_ecommerce.repositories.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductoService extends BaseService<Producto, Long> {


    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository){
        super(productoRepository);
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Page<Producto> getProductoPaginado(Pageable pageable) {
        return productoRepository.getProductoPaginado(pageable);
    }

    @Transactional
    public Producto crearProducto (
            CrearProductoDTO dto
    ) {
        if (dto != null) {
            System.out.println("Modelo: " + dto.getModelo());
        }
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria no encontrada"));

        Producto producto = Producto.builder()
                .modelo(dto.getModelo().toUpperCase(Locale.ROOT))
                .sexo(dto.getSexo())
                .tipoProducto(dto.getTipoProducto())
                .categoria(categoria)
                .detalles(new ArrayList<>())
                .build();
        categoria.getProductos().add(producto);
        productoRepository.save(producto);
        return producto;
    }

    @Transactional
    public Page<Producto> filtroProd (
            String sexo,
            List<Marca> marca,
            List<Integer> talleNumero,
            List<TipoProducto> tipoProducto,
            String nombre,
            List<String> categoria,
            Double min,
            Double max,
            Pageable pageable,
            String orden
    ) throws Exception {

        if ((min != null && max != null) && min > max) {
            throw new Exception("El valor de 'min' no puede ser mayor que 'max'");
        }

        if ((min != null && min <= 0.0) || (max != null && max <= 0.0)) {
            throw new Exception("El valor de 'min' y 'max' no puede ser 0");
        }

        if (orden.equalsIgnoreCase("desc")) {
            return productoRepository.filtrarConPaginadoDesc(sexo, marca, talleNumero, tipoProducto, nombre, categoria, min, max, pageable);
        } else {
            return productoRepository.filtrarConPaginadoAsc(sexo, marca, talleNumero, tipoProducto, nombre, categoria, min, max, pageable);
        }
    }

}
