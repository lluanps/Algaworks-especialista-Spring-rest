package com.luan.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.input.CozinhaInput;
import com.luan.algafoodapi.domain.model.Cozinha;

@Component
public class CozinhaInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Cozinha toDomainObject(CozinhaInput cozinhaIdInput) {
		return mapper.map(cozinhaIdInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInput cozinhaIdInput, Cozinha cozinha) {
		mapper.map(cozinhaIdInput, cozinha);
	}
	
}
