package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;

import com.example.runeshop_ecommerce.services.UsuarioDireccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
@Tag(name = "Usuario Direccion", description = "Direcciones asociadas al usuario y ordenes de compra realizadas por ese usuario")
public class UsuarioDireccionController extends BaseController<UsuarioDireccion, Long> {

    private final UsuarioDireccionService usuarioDireccionService;

    public UsuarioDireccionController(UsuarioDireccionService usuarioDireccionService, UsuarioDireccionService usuarioDireccionService1) {
        super(usuarioDireccionService);
        this.usuarioDireccionService = usuarioDireccionService1;
    }

    @GetMapping("/usuarios/{usuarioId}/direcciones")
    @Operation(
            summary = "Obtener las direcciones de un usuario",
            description = "Controlador para obtener las direcciones de un usuario pasando por URL su id",
            tags = {"GetMapping"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Direcciones obtenidas correctamente",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(
                                                    implementation = Direccion.class
                                            ))
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<List<Direccion>> getDireccionesPorUsuario(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long usuarioId
    ) throws Exception {
        try {
            List<Direccion> direcciones = usuarioDireccionService.direcionesPorUsuario(usuarioId);
            if (direcciones.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(direcciones);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/usuarios/{usuarioId}/direcciones")
    @Operation(
            summary = "Asignar una direccion a un usuario",
            description = "Controlador para crear las direcciones de un usuario y asignarselo pasando por URL el id usuario",
            tags = {"PostMapping"},
            requestBody = @RequestBody(
                    description = "Creacion de la direccion",
                    required = true,
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = Direccion.class
                                    )
                            )
                    }

            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Direcciones obtenidas correctamente",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    implementation = Producto.class
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<?> agregarDireccion(
            @Valid @org.springframework.web.bind.annotation.RequestBody Direccion direccion,
            BindingResult resDireccion,
            @Parameter(description = "ID de usuario", required = true)
            @PathVariable Long usuarioId
    ) throws Exception {
        System.out.println(resDireccion);
        System.out.println(direccion);
        if (resDireccion.hasErrors()) {
            return ResponseEntity.badRequest().body("Campos incorrectos o faltantes");
        }
        try {
            usuarioDireccionService.agregarDireccion(usuarioId, direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Direccion creada y asignada correctamente");

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
