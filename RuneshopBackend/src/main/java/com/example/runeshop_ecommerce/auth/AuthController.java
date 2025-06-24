package com.example.runeshop_ecommerce.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticacion", description = "Controlador para la autentificacion")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    @Operation(
            summary = "Login del usuario",
            description = "Controlador del login",
            tags = {"PostMapping"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nombre y contraseña del usuario",
                    required = true,
                    content = {
                            @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = LoginRequest.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login correcto",
                            content = {
                                    @Content(
                                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                            schema = @Schema(
                                                    implementation = AuthResponse.class
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    @Operation(
            summary = "Register del usuario",
            description = "Controlador del register",
            tags = {"PostMapping"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Campos de registros",
                    required = true,
                    content = {
                            @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = RegisterRequest.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registro correcto",
                            content = {
                                    @Content(
                                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                            schema = @Schema(
                                                    implementation = AuthResponse.class
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }

    @PostMapping("/refresh")
    @Operation(
            summary = "Refresh Token",
            description = "Controlador del refresh token",
            tags = {"PostMapping"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = {
                            @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = RefreshTokenRequest.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registro correcto",
                            content = {
                                    @Content(
                                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                            schema = @Schema(
                                                    implementation = AuthResponse.class
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request.getRefreshToken()));
    }
}
