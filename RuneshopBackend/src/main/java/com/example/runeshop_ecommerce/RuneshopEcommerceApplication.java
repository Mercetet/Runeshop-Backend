package com.example.runeshop_ecommerce;

import com.example.runeshop_ecommerce.entities.*;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.Role;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.repositories.*;
import com.example.runeshop_ecommerce.utils.DotenvLoader;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@EnableScheduling
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class RuneshopEcommerceApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RuneshopEcommerceApplication.class);
        builder.initializers(new DotenvLoader()).run(args);
        System.out.println("Servidor Iniciado");
    }

    @Bean
    @Transactional
    CommandLineRunner init(
            TalleRepository talleRepository,
            ImagenRepository imagenRepository,
            PrecioRepository precioRepository,
            DetalleRepository detalleRepository,
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository,
            DescuentoRepository descuentoRepository,
            DireccionRepository direccionRepository,
            OrdenCompraRepository ordenCompraRepository,
            UsuarioDireccionRepository usuarioDireccionRepository

    ) {
        return args -> {
            try {
                // Talles
                Talle talle = Talle.builder()
                        .numero(43)
                        .build();
                talleRepository.save(talle);

                Talle talle1 = Talle.builder()
                        .numero(42)
                        .build();
                talleRepository.save(talle1);

                Talle talle2 = Talle.builder()
                        .numero(52)
                        .build();
                talleRepository.save(talle2);

                // Categorias
                Categoria categoria = Categoria.builder()
                        .nombre("Urbano")
                        .build();
                categoriaRepository.save(categoria);

                Categoria categoria1 = Categoria.builder()
                        .nombre("Running")
                        .build();
                categoriaRepository.save(categoria1);

                Categoria categoria2 = Categoria.builder()
                        .nombre("Casual")
                        .build();
                categoriaRepository.save(categoria2);

                Categoria categoria3 = Categoria.builder()
                        .nombre("Remeras")
                        .build();
                categoriaRepository.save(categoria3);

                // Imagenes
                Imagen imagen = Imagen.builder()
                        .nombre("Jordan Retro 4")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749617868/kjxjhwehk7etembxcjse.jpg")
                        .build();
                imagenRepository.save(imagen);

                Imagen imagen2 = Imagen.builder()
                        .nombre("Jordan Retro 4 2")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749619095/41eVolXy16L._AC__fltshd.jpg")
                        .build();
                imagenRepository.save(imagen2);

                List<Imagen> imagenesJordan = new ArrayList<>();
                imagenesJordan.add(imagen);
                imagenesJordan.add(imagen2);

                Imagen imagen3 = Imagen.builder()
                        .nombre("Air Max")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749619218/eph_2530-2c36e653bfc7f7f1da16983254406173-480-0_nxlhl9.jpg")
                        .build();
                imagenRepository.save(imagen3);

                Imagen imagen4 = Imagen.builder()
                        .nombre("Air Max 2")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749619195/eph_2529-d3be30ea54c085390f16983254372062-1024-1024_y8elgo.jpg")
                        .build();
                imagenRepository.save(imagen4);

                List<Imagen> imagenesAirMax = new ArrayList<>();
                imagenesAirMax.add(imagen3);
                imagenesAirMax.add(imagen4);

                Imagen imagen5 = Imagen.builder()
                        .nombre("Stan Smith")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749619532/imagen_2025-06-11_022509904_xru6oc.png")
                        .build();
                imagenRepository.save(imagen5);

                Imagen imagen6 = Imagen.builder()
                        .nombre("Stan Smith 2")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749619552/imagen_2025-06-11_022531290_n0v3ko.png")
                        .build();
                imagenRepository.save(imagen6);

                List<Imagen> imagenesStanSmith = new ArrayList<>();
                imagenesStanSmith.add(imagen5);
                imagenesStanSmith.add(imagen6);

                Imagen imagen7 = Imagen.builder()
                        .nombre("Remera Nike")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749620156/imagen_2025-06-11_023533932_rvwmi4.png")
                        .build();
                imagenRepository.save(imagen7);

                Imagen imagen8 = Imagen.builder()
                        .nombre("Remera Nike 2")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749620193/imagen_2025-06-11_023612409_fgszf9.png")
                        .build();
                imagenRepository.save(imagen8);

                List<Imagen> imagenesRemera = new ArrayList<>();
                imagenesRemera.add(imagen7);
                imagenesRemera.add(imagen8);

                Imagen imagen9 = Imagen.builder()
                        .nombre("Pantalon Adidas")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749620378/imagen_2025-06-11_023917928_oqhstp.png")
                        .build();
                imagenRepository.save(imagen9);

                Imagen imagen10 = Imagen.builder()
                        .nombre("Pantalon Adidas 2")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749620398/imagen_2025-06-11_023937688_nsgzkj.png")
                        .build();
                imagenRepository.save(imagen10);

                List<Imagen> imagenesPantalon = new ArrayList<>();
                imagenesPantalon.add(imagen9);
                imagenesPantalon.add(imagen10);

                Imagen imagen11 = Imagen.builder()
                        .nombre("Campera Reebok")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749620626/imagen_2025-06-11_024325192_zpwy3r.png")
                        .build();
                imagenRepository.save(imagen11);

                Imagen imagen12 = Imagen.builder()
                        .nombre("Campera Reebok 2")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749620658/imagen_2025-06-11_024357613_ob9hfy.png")
                        .build();
                imagenRepository.save(imagen12);
                
                List<Imagen> imagenesCampera = new ArrayList<>();
                imagenesCampera.add(imagen11);
                imagenesCampera.add(imagen12);

                Imagen imagen13 = Imagen.builder()
                        .nombre("Botin Puma Future")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749620982/imagen_2025-06-11_024905094_kyyc2l.png")
                        .build();
                imagenRepository.save(imagen13);

                Imagen imagen14 = Imagen.builder()
                        .nombre("Botin Puma Future 2")
                        .imagenUrl("https://res.cloudinary.com/dpyfse8qb/image/upload/v1749621001/imagen_2025-06-11_024940398_o2fo2b.png")
                        .build();
                imagenRepository.save(imagen14);

                List<Imagen> imagenesBotin = new ArrayList<>();
                imagenesBotin.add(imagen13);
                imagenesBotin.add(imagen14);

                // Precios
                Precio precio1 = Precio.builder()
                        .precioCompra(450000.0)
                        .precioVenta(550000.0)
                        .build();
                precioRepository.save(precio1);

                Precio precio2 = Precio.builder()
                        .precioCompra(180000.0)
                        .precioVenta(220000.0)
                        .build();
                precioRepository.save(precio2);

                Precio precio3 = Precio.builder()
                        .precioCompra(120000.0)
                        .precioVenta(150000.0)
                        .build();
                precioRepository.save(precio3);

                Precio precio4 = Precio.builder()
                        .precioCompra(25000.0)
                        .precioVenta(35000.0)
                        .build();
                precioRepository.save(precio4);

                Precio precio5 = Precio.builder()
                        .precioCompra(45000.0)
                        .precioVenta(60000.0)
                        .build();
                precioRepository.save(precio5);

                Precio precio6 = Precio.builder()
                        .precioCompra(75000.0)
                        .precioVenta(95000.0)
                        .build();
                precioRepository.save(precio6);

                Precio precio7 = Precio.builder()
                        .precioCompra(85000.0)
                        .precioVenta(115000.0)
                        .build();
                precioRepository.save(precio7);

                // Productos
                Producto jordan = Producto.builder()
                        .modelo("Air Jordan 4 Retro")
                        .sexo("Hombre")
                        .tipoProducto(TipoProducto.ZAPATILLA)
                        .categoria(categoria)
                        .build();
                productoRepository.save(jordan);

                Producto airmax = Producto.builder()
                        .modelo("Nike Air Max Plus")
                        .sexo("Hombre")
                        .tipoProducto(TipoProducto.ZAPATILLA)
                        .categoria(categoria1)
                        .build();
                productoRepository.save(airmax);

                Producto stansmith = Producto.builder()
                        .modelo("Adidas Stan Smith")
                        .sexo("Hombre")
                        .tipoProducto(TipoProducto.ZAPATILLA)
                        .categoria(categoria2)
                        .build();
                productoRepository.save(stansmith);

                Producto remera = Producto.builder()
                        .modelo("Nike Sportswear")
                        .sexo("Hombre")
                        .tipoProducto(TipoProducto.REMERA)
                        .categoria(categoria3)
                        .build();
                productoRepository.save(remera);

                Producto pantalon = Producto.builder()
                        .modelo("Adidas Training")
                        .sexo("Hombre")
                        .tipoProducto(TipoProducto.PANTALON)
                        .categoria(categoria3)
                        .build();
                productoRepository.save(pantalon);

                Producto campera = Producto.builder()
                        .modelo("Reebok Classic")
                        .sexo("Unisex")
                        .tipoProducto(TipoProducto.CAMPERA)
                        .categoria(categoria2)
                        .build();
                productoRepository.save(campera);

                Producto botin = Producto.builder()
                        .modelo("Puma Future Ultimate")
                        .sexo("Hombre")
                        .tipoProducto(TipoProducto.BOTIN)
                        .categoria(categoria)
                        .build();
                productoRepository.save(botin);

                // Detalles
                Detalle detalle1 = Detalle.builder()
                        .marca(Marca.NIKE)
                        .stock(10)
                        .color("Negro/Gris")
                        .producto(jordan)
                        .precio(precio1)
                        .talle(talle)
                        .imagenes(imagenesJordan)
                        .build();
                detalleRepository.save(detalle1);

                Detalle detalle2 = Detalle.builder()
                        .marca(Marca.NIKE)
                        .stock(15)
                        .color("Blanco/Negro")
                        .producto(airmax)
                        .precio(precio2)
                        .talle(talle1)
                        .imagenes(imagenesAirMax)
                        .build();
                detalleRepository.save(detalle2);

                Detalle detalle3 = Detalle.builder()
                        .marca(Marca.ADIDAS)
                        .stock(25)
                        .color("Blanco/Verde")
                        .producto(stansmith)
                        .precio(precio3)
                        .talle(talle2)
                        .imagenes(imagenesStanSmith)
                        .build();
                detalleRepository.save(detalle3);

                Detalle detalle4 = Detalle.builder()
                        .marca(Marca.NIKE)
                        .stock(30)
                        .color("Negro")
                        .producto(remera)
                        .precio(precio4)
                        .talle(talle)
                        .imagenes(imagenesRemera)
                        .build();
                detalleRepository.save(detalle4);

                Detalle detalle5 = Detalle.builder()
                        .marca(Marca.ADIDAS)
                        .stock(20)
                        .color("Gris")
                        .producto(pantalon)
                        .precio(precio5)
                        .talle(talle1)
                        .imagenes(imagenesPantalon)
                        .build();
                detalleRepository.save(detalle5);

                Detalle detalle6 = Detalle.builder()
                        .marca(Marca.REEBOK)
                        .stock(15)
                        .color("Negro/Rojo")
                        .producto(campera)
                        .precio(precio6)
                        .talle(talle)
                        .imagenes(imagenesCampera)
                        .build();
                detalleRepository.save(detalle6);

                Detalle detalle7 = Detalle.builder()
                        .marca(Marca.PUMA)
                        .stock(12)
                        .color("Amarillo/Negro")
                        .producto(botin)
                        .precio(precio7)
                        .talle(talle)
                        .imagenes(imagenesBotin)
                        .build();
                detalleRepository.save(detalle7);

                // Descuentos
                Descuento noDescuento = Descuento.builder()
                        .porcentaje("Sin descuento")
                        .valor(0.0)
                        .build();
                descuentoRepository.save(noDescuento);

                Descuento descuento10 = Descuento.builder()
                        .porcentaje("10")
                        .valor(0.10)
                        .build();
                descuentoRepository.save(descuento10);

                Descuento descuento20 = Descuento.builder()
                        .porcentaje("20")
                        .valor(0.20)
                        .build();
                descuentoRepository.save(descuento20);

                Descuento descuento30 = Descuento.builder()
                        .porcentaje("30")
                        .valor(0.30)
                        .build();
                descuentoRepository.save(descuento30);

                Descuento descuento40 = Descuento.builder()
                        .porcentaje("40")
                        .valor(0.40)
                        .build();
                descuentoRepository.save(descuento40);

                Descuento descuento50 = Descuento.builder()
                        .porcentaje("50")
                        .valor(0.50)
                        .build();
                descuentoRepository.save(descuento50);
            
                
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        };
    }

}