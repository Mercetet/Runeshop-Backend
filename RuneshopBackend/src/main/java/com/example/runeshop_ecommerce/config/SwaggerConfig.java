package com.example.runeshop_ecommerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
		info = @Info(
				title = "Runeshop Ecommerce",
				description = "Runeshop es una API RESTful desarrollada con Java Spring Boot que sigue una arquitectura por capas bien definida. La aplicación está estructurada en cuatro capas principales: entidades, controladores, servicios y repositorios. Cada una de estas capas cuenta con una clase o interfaz genérica reutilizable que encapsula las operaciones básicas de un CRUD, permitiendo que las clases específicas hereden y extiendan dicha funcionalidad de manera eficiente.\n" +
						"\n" +
						"En cuanto a la seguridad, se implementa Spring Security en conjunto con JSON Web Tokens (JWT) para gestionar los procesos de autenticación y autorización. Además, se incluye un controlador global de errores mediante el uso de @RestControllerAdvice, así como tareas programadas utilizando la anotación @Scheduled. También se han configurado varios beans personalizados para distintas necesidades internas del proyecto.\n" +
						"\n" +
						"Finalmente, la API se integra con servicios externos como la plataforma de pagos MercadoPago, utilizada para el procesamiento de transacciones, y el servicio de Cloudinary, empleado para la carga y gestión de imágenes.",
				version = "1.0.0",
				summary = "Runeshop es un ecommerce de venta de ropa",
				contact = @Contact(
						name = "vale.source.03@gmail.com",
						email = "vale.source.03@gmail.com"
				)
		),
		servers = {
				@Server(
						description = "Dev Server",
						url = "http://localhost:8080"
				)
		},
		security = @SecurityRequirement(
				name = "Security Token"
		)
)

@SecurityScheme(
	name = "Security Token",
		description = "Access Token for my API",
		type = SecuritySchemeType.HTTP,
		paramName = HttpHeaders.AUTHORIZATION,
		in = SecuritySchemeIn.HEADER,
		scheme = "Bearer",
		bearerFormat = "JWT"
)
public class SwaggerConfig {
}
