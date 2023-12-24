package com.luan.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.luan.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Estado;
import com.luan.algafoodapi.domain.repository.EstadoRepository;
import com.luan.algafoodapi.domain.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository repository;
	
	@Autowired
	private EstadoService service;
	
	@GetMapping
	public List<Estado> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public Estado findEstadoById(@PathVariable Long estadoId) {
		return service.buscaOuFalha(estadoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody Estado estado) {
		try {
			return service.salvar(estado);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@RequestBody Estado estado, @PathVariable Long id) {
		try {
			Estado estadoAtual = service.buscaOuFalha(id);
			
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			return service.salvar(estadoAtual);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId) {
		service.excluir(estadoId);
	}
	
}
