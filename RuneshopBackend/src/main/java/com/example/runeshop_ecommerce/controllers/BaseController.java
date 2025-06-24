package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Base;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.services.BaseService;
import com.fasterxml.classmate.GenericType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<E extends Base, ID extends Serializable> {

    protected BaseService<E, ID> service;

    @GetMapping()
    @Operation(
            summary = "Obtener todas las entidades",
            description = "Metodo HTTP generico que heredan todas las entidades",
            tags = {"GetMapping"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entidades obtenidas y devueltas de forma correcta",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = GenericType.class))
                            )
                    )
            }
    )
    public ResponseEntity<List<E>> findAll() throws Exception {
        List<E> entities = service.findAll();
        return ResponseEntity.ok(entities);
    }

    public BaseController(BaseService<E, ID> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener entidades por ID",
            description = "Metodo HTTP generico que heredan todas las entidades",
            tags = {"GetMapping"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entidad encontrada por ID y devuelta de forma correcta",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = GenericType.class
                                    )
                            )
                    )
            }
    )
    public E findByID(
            @Parameter(description = "ID de la entidad", required = true)
            @PathVariable ID id
    ) {
        return service.findByID(id);
    }

    @PostMapping()
    @Operation(
            summary = "Crear una entidad",
            description = "Metodo HTTP generico que heredan todas las entidades",
            tags = {"PostMapping"},
            requestBody = @RequestBody(
                    description = "Creacion de la entidad a partir de los datos pasados",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = GenericType.class
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Entidad creada correctamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = GenericType.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<E> create(@org.springframework.web.bind.annotation.RequestBody E entity) throws Exception {
        System.out.println(entity);
        E newEntity = service.create(entity);
        return ResponseEntity.status(201).body(newEntity);
    }

    @PutMapping()
    @Operation(
            summary = "Modifica una entidad",
            description = "Metodo HTTP generico que heredan todas las entidades",
            tags = {"PutMapping"},
            requestBody = @RequestBody(
                    description = "La entidad se edita solo con los campos modificados. Es necesario pasasar el body completo incluido el ID",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = GenericType.class
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entidad editada correctamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = GenericType.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<E> update(@org.springframework.web.bind.annotation.RequestBody E data) throws Exception {
        E updateEntity = service.update(data);
        return ResponseEntity.ok(updateEntity);
    }

    //Borrado Logico
    @PutMapping("/{id}")
    @Operation(
            summary = "Borra logicamente una entidad",
            description = "Metodo HTTP generico que heredan todas las entidades",
            tags = {"PutMapping"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Entidad borrada correctamente",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseEntity.class
                                    )
                            )
                    )
            }
    )
    public void logicDelete(
            @Parameter(description = "ID de la entidad", required = true)
            @PathVariable ID id
    ) throws Exception {
        service.logicDeletion(id);
    }
}
