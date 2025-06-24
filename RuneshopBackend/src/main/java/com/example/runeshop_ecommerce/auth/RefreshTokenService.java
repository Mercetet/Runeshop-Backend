package com.example.runeshop_ecommerce.auth;

import com.example.runeshop_ecommerce.entities.RefreshToken;
import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.exception.ExpirationRefreshTokenException;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.repositories.RefreshTokenRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;
	private final UsuarioRepository usuarioRepository;

	@Transactional
	public RefreshToken createRefreshToken(String username) {
		Usuario usuario = usuarioRepository.findUsuarioByNombreUsuario(username)
				.orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

		deleteByUsuario(usuario);

		RefreshToken refreshToken = RefreshToken.builder()
				.usuario(usuario)
				.token(UUID.randomUUID().toString())
				.expirationDate(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 7))
				.build();

		return refreshTokenRepository.save(refreshToken);
	}

	@Transactional
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	@Transactional
	public RefreshToken verifyRefreshTokenExpiration(RefreshToken token) {
		if (token.getExpirationDate().before(new Date())) {
			refreshTokenRepository.delete(token);
			throw new ExpirationRefreshTokenException("Refresh token ha expirado");
		}
		return token;
	}

	@Transactional
	public void deleteByToken(String token) {
		refreshTokenRepository.deleteByToken(token);
	}

	@Transactional
	public void deleteByUsuario(Usuario usuario) {
		refreshTokenRepository.deleteByUsuario(usuario);
	}
}
