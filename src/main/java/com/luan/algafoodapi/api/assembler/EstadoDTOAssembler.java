package com.luan.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.EstadoDTO;
import com.luan.algafoodapi.domain.model.Estado;

@Component
public class EstadoDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public EstadoDTO toModel(Estado estado) {
		return mapper.map(estado, EstadoDTO.class);
	}
	
	public List<EstadoDTO> toCollectionDto(List<Estado> estados) {
		return estados.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
	}
	
}
