package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.algafoodapi.api.assembler.CidadeDTOAssembler;
import com.luan.algafoodapi.api.assembler.CidadeInputDisassembler;
import com.luan.algafoodapi.api.model.CidadeDTO;
import com.luan.algafoodapi.api.model.input.CidadeInput;
import com.luan.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.repository.CidadeRepository;
import com.luan.algafoodapi.domain.service.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cidades")
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
	
	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CidadeDTO> listar() {
		List<Cidade> findAll = repository.findAll();
		
		return cidadeDTOAssembler.toCollectionDto(findAll);
	}

	@ApiOperation("Busca uma cidade po Id")
	@GetMapping("/cidadeId")
	public CidadeDTO cidadeById(@PathVariable Long cidadeId) {
		Cidade cidade = service.buscarOuFalhar(cidadeId);
		
		return cidadeDTOAssembler.toModel(cidade);
	}
	
	@ApiOperation("Cadastra uma cidade")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO salvar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = service.salvar(cidade);
			
			return cidadeDTOAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@ApiOperation("Atualiza uma cidade por Id")
	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@PathVariable @Valid Long cidadeId,
			@RequestBody CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = service.buscarOuFalhar(cidadeId);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			cidadeAtual =  service.salvar(cidadeAtual);
			
			return cidadeDTOAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@ApiOperation("Exclui uma cidade por Id")
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		service.excluir(cidadeId);	
	}
	
}
