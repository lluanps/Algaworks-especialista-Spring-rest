package com.luan.algafoodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.controller.UsuarioController;
import com.luan.algafoodapi.api.controller.UsuarioGrupoControlller;
import com.luan.algafoodapi.api.model.UsuarioDTO;
import com.luan.algafoodapi.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO> {

	@Autowired
	private ModelMapper mapper;
	
	public UsuarioDTOAssembler() {
		super(UsuarioController.class, UsuarioDTO.class);
	}
	
	@Override
	public UsuarioDTO toModel(Usuario usuario) {
		UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);
		
		mapper.map(usuario, usuarioDTO);
		
		usuarioDTO.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UsuarioController.class).listar()).withRel("usuarios"));
		
		usuarioDTO.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(UsuarioGrupoControlller.class).listar(usuario.getId())).withRel("grupo-usuario"));
		
		return usuarioDTO;
	}
	
	@Override
	public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withSelfRel());
	}
	
}
