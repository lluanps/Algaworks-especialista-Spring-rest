package com.luan.algafoodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luan.algafoodapi.api.model.FormaPagamentoDTO;
import com.luan.algafoodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
		return mapper.map(formaPagamento, FormaPagamentoDTO.class);
	}
	
	public List<FormaPagamentoDTO> toCollectionDto(Collection<FormaPagamento> formaPagamentos) {
		return formaPagamentos.stream()
				.map(formaPagamento -> toModel(formaPagamento))
				.collect(Collectors.toList());
	}
	
}
