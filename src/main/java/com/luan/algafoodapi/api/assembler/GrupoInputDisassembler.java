package com.luan.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.input.GrupoInput;
import com.luan.algafoodapi.domain.model.Grupo;

@Component
public class GrupoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Grupo toDomainObject(GrupoInput grupoInput) {
		return mapper.map(grupoInput, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
		mapper.map(grupoInput, grupo);
	}
	
}
