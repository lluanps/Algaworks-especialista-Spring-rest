package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.luan.algafoodapi.api.assembler.EstadoDTOAssembler;
import com.luan.algafoodapi.api.assembler.EstadoInputDisassembler;
import com.luan.algafoodapi.api.model.EstadoDTO;
import com.luan.algafoodapi.api.model.input.EstadoInput;
import com.luan.algafoodapi.domain.model.Estado;
import com.luan.algafoodapi.domain.repository.EstadoRepository;
import com.luan.algafoodapi.domain.service.EstadoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/estados")
@Tag(name = "Estado")
public class EstadoController {

	@Autowired
	private EstadoRepository repository;
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private EstadoDTOAssembler estadoDTOAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@GetMapping
	public CollectionModel<EstadoDTO> listar() {
		 List<Estado> findAll = repository.findAll();
		 
		 return estadoDTOAssembler.toCollectionModel(findAll);
	}
	
	@GetMapping("/{estadoId}")
	public EstadoDTO findEstadoById(@PathVariable Long estadoId) {
		Estado estado = service.buscaOuFalha(estadoId);
		
		return estadoDTOAssembler.toModel(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoDTO salvar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		
		estado = service.salvar(estado);
		
		return estadoDTOAssembler.toModel(estado);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoDTO atualizar(@RequestBody @Valid EstadoInput estadoInput, @PathVariable Long estadoId) {
		Estado estadoAtual = service.buscaOuFalha(estadoId);
		
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		estadoAtual = service.salvar(estadoAtual);
		
		return estadoDTOAssembler.toModel(estadoAtual);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId) {
		service.excluir(estadoId);
	}
	
}
