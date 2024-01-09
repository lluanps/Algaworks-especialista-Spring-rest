package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.luan.algafoodapi.domain.model.Cidade;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.repository.CidadeRepository;
import com.luan.algafoodapi.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService service;
	
	@Autowired
	private CidadeRepository repository;
	
	@GetMapping
	public List<Cidade> listar() {
		return repository.findAll();
	}

	@GetMapping("/cidadeId")
	public Cidade cozinhaById(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		return service.buscarOuFalhar(cidadeId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody @Valid Cidade cidade) {
		try {
			return service.salvar(cidade);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable @Valid Long cidadeId,
			@RequestBody Cidade cidade) {
		try {
			Cidade cidadeAtual = service.buscarOuFalhar(cidadeId);
			
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			
			return service.salvar(cidadeAtual);
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
