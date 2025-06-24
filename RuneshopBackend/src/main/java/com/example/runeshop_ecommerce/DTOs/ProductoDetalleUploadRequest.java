package com.example.runeshop_ecommerce.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDetalleUploadRequest {
	// Clase auxiliar para documentar con Swagger

	@Schema(type = "string", format = "binary", description = "Archivo a subir")
	private List<MultipartFile> files;

	@Schema(implementation = CrearProductoDTO.class, description = "Datos del producto")
	private CrearProductoDTO productoDTO;

	@Schema(implementation = CrearDetalleDTO.class, description = "Datos del detalle")
	private CrearDetalleDTO detalleDTO;

}
