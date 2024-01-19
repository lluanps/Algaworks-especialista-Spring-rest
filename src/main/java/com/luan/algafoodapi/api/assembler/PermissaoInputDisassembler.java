package com.luan.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.input.PermissaoInput;
import com.luan.algafoodapi.domain.model.Permissao;

@Component
public class PermissaoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Permissao toDomainObject(PermissaoInput permissaoInput) {
		return mapper.map(permissaoInput, Permissao.class);
	}
	
	public void copyToDomainObject(PermissaoInput permissaoInput, Permissao permissao) {
		mapper.map(permissaoInput, permissao);
	}
	
}
