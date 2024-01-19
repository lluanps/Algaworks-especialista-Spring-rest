package com.luan.algafoodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.PermissaoDTO;
import com.luan.algafoodapi.domain.model.Permissao;

@Component
public class PermissaoDTOAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public PermissaoDTO toModel(Permissao permissao) {
		return mapper.map(permissao, PermissaoDTO.class);
	}
	
	public List<PermissaoDTO> toCollectionDto(Collection<Permissao> permissoes) {
		return permissoes.stream()
				.map(permissao -> toModel(permissao))
				.collect(Collectors.toList());
	}

}
