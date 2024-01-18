package com.luan.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.ProdutoDTO;
import com.luan.algafoodapi.domain.model.Produto;

@Component
public class ProdutoDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public ProdutoDTO toModel(Produto produto) {
		return mapper.map(produto, ProdutoDTO.class);
	}
	
	public List<ProdutoDTO> toCollectionDTO(List<Produto> produtos) {
		return produtos.stream()
				.map(produto -> toModel(produto))
				.collect(Collectors.toList());
	}
	
}
