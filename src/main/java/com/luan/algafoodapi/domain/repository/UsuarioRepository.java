package com.luan.algafoodapi.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.luan.algafoodapi.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	
	//busca por email
	Optional<Usuario> findByEmail(String email);

}
