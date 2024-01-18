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

import com.luan.algafoodapi.api.assembler.RestauranteDTOAssembler;
import com.luan.algafoodapi.api.assembler.RestauranteInputDisassembler;
import com.luan.algafoodapi.api.model.RestauranteDTO;
import com.luan.algafoodapi.api.model.input.RestauranteInput;
import com.luan.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;
import com.luan.algafoodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService service;
	
	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private RestauranteDTOAssembler dtoAssembler;
	
	@Autowired
	private RestauranteInputDisassembler inputDisassembler;
	
	@GetMapping
	public List<RestauranteDTO> findAll() { 
		return dtoAssembler.toCollectionDto(repository.findAll());
	}
	
	@GetMapping("/{restaurantedId}")
	public RestauranteDTO buscarRestaurantePorId(@PathVariable Long restaurantedId) {
		Restaurante restaurante = service.buscaOuFalha(restaurantedId);
		return dtoAssembler.toModel(restaurante);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDTO save(@RequestBody @Valid RestauranteInput restauranteInput) {

		try {
			Restaurante restaurante = inputDisassembler.toDomainObject(restauranteInput);
			
			return dtoAssembler.toModel(service.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restauranteAtual = service.buscaOuFalha(restauranteId);
			
			inputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
			
			return dtoAssembler.toModel(service.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		service.ativar(restauranteId);
	}
	
	@DeleteMapping("/{restauranteId}/inativar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		service.inativar(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/aberto")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		service.abrir(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/fechar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		service.fechar(restauranteId);
	}
	
}
