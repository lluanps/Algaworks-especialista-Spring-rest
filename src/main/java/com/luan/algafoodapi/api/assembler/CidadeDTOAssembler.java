package com.luan.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.CidadeDTO;
import com.luan.algafoodapi.domain.model.Cidade;

@Component
public class CidadeDTOAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public CidadeDTO toModel(Cidade cidade) {
		return mapper.map(cidade, CidadeDTO.class);
	}
	
	public List<CidadeDTO> toCollectionDto(List<Cidade> cidades) {
		return cidades.stream()
				.map(cidade -> toModel(cidade))
				.collect(Collectors.toList());
	}

}
