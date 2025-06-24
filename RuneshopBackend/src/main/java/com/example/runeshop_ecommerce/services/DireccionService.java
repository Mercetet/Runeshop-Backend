package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.repositories.DireccionRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioDireccionRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DireccionService extends BaseService<Direccion, Long> {


	public DireccionService(DireccionRepository direccionRepository) {
		super(direccionRepository);
    }

}
