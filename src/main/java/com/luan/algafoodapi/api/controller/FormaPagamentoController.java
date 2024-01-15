package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import com.luan.algafoodapi.api.assembler.FormaPagamentoInputDisassembler;
import com.luan.algafoodapi.api.model.FormaPagamentoDTO;
import com.luan.algafoodapi.api.model.input.FormaPagamentoInput;
import com.luan.algafoodapi.domain.model.FormaPagamento;
import com.luan.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.luan.algafoodapi.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService service;
	
	@Autowired
	private FormaPagamentoRepository repository;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

	@GetMapping
	public List<FormaPagamentoDTO> listar() {
		List<FormaPagamento> buscaTodos = repository.findAll();
		
		return formaPagamentoDTOAssembler.toCollectionDto(buscaTodos);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO buscarPorId(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = service.buscaOuFalha(formaPagamentoId);
		return formaPagamentoDTOAssembler.toModel(formaPagamento);
	}
	
	@PostMapping
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
		formaPagamento = service.salvar(formaPagamento);
		return formaPagamentoDTOAssembler.toModel(formaPagamento);
	}

}
