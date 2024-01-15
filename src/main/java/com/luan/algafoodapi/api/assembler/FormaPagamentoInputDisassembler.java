package com.luan.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.input.EstadoInput;
import com.luan.algafoodapi.api.model.input.FormaPagamentoInput;
import com.luan.algafoodapi.domain.model.Estado;
import com.luan.algafoodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
		return mapper.map(formaPagamentoInput, FormaPagamento.class);
	}
	
	public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
		mapper.map(formaPagamentoInput, formaPagamento);
	}
	
}
