package com.luan.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.input.ProdutoInput;
import com.luan.algafoodapi.domain.model.Produto;

@Component
public class ProdutoInputDisassembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public Produto toDomainObject(ProdutoInput produtoInput) {
		return mapper.map(produtoInput, Produto.class);
	}
	
    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
    	mapper.map(produtoInput, produto);
    }  

}
