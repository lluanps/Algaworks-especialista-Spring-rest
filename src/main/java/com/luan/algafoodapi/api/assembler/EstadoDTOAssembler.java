package com.luan.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.controller.EstadoController;
import com.luan.algafoodapi.api.model.EstadoDTO;
import com.luan.algafoodapi.domain.model.Estado;

@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

	@Autowired
	private ModelMapper mapper;

	public EstadoDTOAssembler() {
		super(EstadoController.class, EstadoDTO.class);
	}
	
	@Override
	public EstadoDTO toModel(Estado estado) {
		EstadoDTO estadoDTO = createModelWithId(estado.getId(), estado);
		
		mapper.map(estado, estadoDTO);
		
		estadoDTO.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(EstadoController.class).listar()).withRel("estados"));
		
		return estadoDTO;
	}
	
	@Override
	public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(EstadoController.class).withSelfRel());
	}
	
}
