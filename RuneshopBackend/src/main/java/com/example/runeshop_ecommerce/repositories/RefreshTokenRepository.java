package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.RefreshToken;
import com.example.runeshop_ecommerce.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);
	void deleteByUsuario(Usuario usuario);
	void deleteByToken(String token);
}
