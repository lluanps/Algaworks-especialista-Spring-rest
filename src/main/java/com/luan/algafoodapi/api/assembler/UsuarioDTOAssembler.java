package com.luan.algafoodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.UsuarioDTO;
import com.luan.algafoodapi.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public UsuarioDTO toModel(Usuario usuario) {
		return mapper.map(usuario, UsuarioDTO.class);
	}
	
	public List<UsuarioDTO> toCollectionDto(Collection<Usuario> usuarios) {
		return usuarios.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}
	
}
