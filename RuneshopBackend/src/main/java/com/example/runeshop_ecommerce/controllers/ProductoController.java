package com.example.runeshop_ecommerce.controllers;


import com.example.runeshop_ecommerce.DTOs.CrearDetalleDTO;
import com.example.runeshop_ecommerce.DTOs.CrearProductoDTO;
import com.example.runeshop_ecommerce.DTOs.GetProductoFilterDTO;
import com.example.runeshop_ecommerce.DTOs.ProductoDetalleUploadRequest;
import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.services.DetalleService;
import com.example.runeshop_ecommerce.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/producto")
@Tag(name = "Producto", description = "Controlador de productos")
public class ProductoController extends BaseController<Producto, Long> {

    private final ProductoService productoService;
    private final DetalleService detalleService;

    public ProductoController(ProductoService productoService, ProductoService productoService1, DetalleService detalleService) {
        super(productoService);
        this.productoService = productoService1;
        this.detalleService = detalleService;
    }

    @GetMapping("/paginado")
    @Operation(
            summary = "Obtencion de los productos paginados",
            description = "Metodo Get HTTP para obtener los productos paginados",
            tags = {"GetMapping"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Productos traidos correctamente",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(
                                                    implementation = Producto.class
                                            ))
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Page<Producto>> getProductoPaginado(
            @Parameter(description = "Pagina incial (valor por default = 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Cantidad de paginas (valor por default = 10)")
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        try {
            Pageable pageRequest = PageRequest.of(page, size);

            Page<Producto> productoPage = productoService.getProductoPaginado(pageRequest);

            if (productoPage.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(productoPage);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping( "/crear_producto")
    @Operation(
            summary = "Creacion del producto",
            description = "Controlador que recibe 3 parametros, el DTO de detalle, el DTO de producto y la/las imagen/es",
            tags = {"PostMapping"},
            requestBody = @RequestBody(
                    description = "DTOs de producto y detalle, junto con la imagen",
                    required = true,
                    content = {
                            @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = ProductoDetalleUploadRequest.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Producto creado correctamente",
                            content = {
                                    @Content(
                                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                            schema = @Schema(
                                                    implementation = Detalle.class
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Detalle> crearProducto (
            @RequestPart(value = "imagen") List<MultipartFile> files,
            @RequestPart("producto") CrearProductoDTO productoDTO,
            @RequestPart("detalle") CrearDetalleDTO detalleDTO
            ) throws Exception {
        try {
            Producto producto = productoService.crearProducto(productoDTO);
            Detalle detalle = detalleService.crearDetalle(files, detalleDTO, producto);

            return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/filtro")
    @Operation(
            summary = "Filtros opcionales de productos",
            description = "Controlador para poder filtrar productos de los campos seleccionados, pueden elegirse varios campos del una categoria (Multiples marcas, tipoProductos, etc)",
            tags = {"GetMapping"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Productos filtrados correctamente",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(
                                                    implementation = Producto.class
                                            ))
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Page<Producto>> filtroProducto(
            @ParameterObject
            @ModelAttribute GetProductoFilterDTO prod,

            @Parameter(description = "Orden en que los datos deben ser traidos (asc o desc) por default = asc")
            @RequestParam(defaultValue = "asc") String orden,

            @Parameter(description = "Pagina incial (valor por default = 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Cantidad de paginas (valor por default = 10)")
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        try {
            Pageable pageable = PageRequest.of(page, size);

            String modeloNormalized = (prod.getModelo() != null)
                    ? prod.getModelo().toUpperCase(Locale.ROOT)
                    : null;

            Page<Producto> productos = productoService.filtroProd(
                    prod.getSexo(),
                    prod.getMarca(),
                    prod.getTalleNumero(),
                    prod.getTipoProducto(),
                    modeloNormalized,
                    prod.getCategoria(),
                    prod.getMin(),
                    prod.getMax(),
                    pageable,
                    orden);
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
