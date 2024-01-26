package com.luan.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.FotoProdutoDTO;
import com.luan.algafoodapi.domain.model.FotoProduto;

@Component
public class FotoProdutoDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FotoProdutoDTO toModel(FotoProduto fotoProduto) {
		return mapper.map(fotoProduto, FotoProdutoDTO.class);
	}
	
}
