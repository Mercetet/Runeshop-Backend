package com.example.runeshop_ecommerce.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleUploadRequest {

	@Schema(implementation = CrearDetalleDTO.class, description = "Datos del detalle")
	private CrearDetalleDTO detalleDTO;

}
