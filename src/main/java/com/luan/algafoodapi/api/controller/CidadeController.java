package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.ResourceURIHelper;
import com.luan.algafoodapi.api.assembler.CidadeDTOAssembler;
import com.luan.algafoodapi.api.assembler.CidadeInputDisassembler;
import com.luan.algafoodapi.api.model.CidadeDTO;
import com.luan.algafoodapi.api.model.input.CidadeInput;
import com.luan.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.repository.CidadeRepository;
import com.luan.algafoodapi.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService service;
	
	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private CidadeDTOAssembler cidadeDTOAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@GetMapping
	public CollectionModel<CidadeDTO> listar() {
		List<Cidade> findAll = repository.findAll();
		
		return cidadeDTOAssembler.toCollectionModel(findAll);
	}

	@GetMapping(value = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeDTO cidadeById(@PathVariable Long cidadeId) {
		Cidade cidade = service.buscarOuFalhar(cidadeId);
		
		return cidadeDTOAssembler.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO salvar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = service.salvar(cidade);
			
			CidadeDTO cidadeDTO =  cidadeDTOAssembler.toModel(cidade);
			
			ResourceURIHelper.addUriResponseHeader(cidadeDTO.getId());
			
			return cidadeDTO;
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@PathVariable @Valid Long cidadeId, @RequestBody CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = service.buscarOuFalhar(cidadeId);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			cidadeAtual =  service.salvar(cidadeAtual);
			
			return cidadeDTOAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		service.excluir(cidadeId);	
	}
	
}
