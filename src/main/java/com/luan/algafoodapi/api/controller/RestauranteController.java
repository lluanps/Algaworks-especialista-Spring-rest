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

import com.fasterxml.jackson.annotation.JsonView;
import com.luan.algafoodapi.api.assembler.RestauranteDTOAssembler;
import com.luan.algafoodapi.api.assembler.RestauranteInputDisassembler;
import com.luan.algafoodapi.api.model.RestauranteDTO;
import com.luan.algafoodapi.api.model.input.RestauranteInput;
import com.luan.algafoodapi.api.model.view.RestauranteView;
import com.luan.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;
import com.luan.algafoodapi.domain.service.RestauranteService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/restaurantes")
@Tag(name = "Restaurante")
public class RestauranteController {
	
	@Autowired
	private RestauranteService service;
	
	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private RestauranteDTOAssembler dtoAssembler;
	
	@Autowired
	private RestauranteInputDisassembler inputDisassembler;
	
	@JsonView(RestauranteView.Resumo.class)
	@GetMapping
	public List<RestauranteDTO> listar() { 
		return dtoAssembler.toCollectionDto(repository.findAll());
	}

	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteDTO> listarApenasNome() { 
		return listar();
	}

	/*
	@GetMapping
	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) { 
		List<Restaurante> restaurantes = repository.findAll();
		List<RestauranteDTO> restauranteDTOs =  dtoAssembler.toCollectionDto(restaurantes);

		MappingJacksonValue restauranteWrapper = new MappingJacksonValue(restauranteDTOs);
		
		restauranteWrapper.setSerializationView(RestauranteView.Resumo.class);
		
		if ("apenas-nomes".equals(projecao)) {
			restauranteWrapper.setSerializationView(RestauranteView.ApenasNome.class);
		} else if ("copmleto".equals(projecao)) {
			restauranteWrapper.setSerializationView(null);
		}
		
		return restauranteWrapper;
	}
	*/
	
	/*
	@GetMapping
	public List<RestauranteDTO> listar() { 
		return dtoAssembler.toCollectionDto(repository.findAll());
	}

	@JsonView(RestauranteView.Resumo.class)
	@GetMapping(params = "projecao=resumo")
	public List<RestauranteDTO> listarResumo() { 
		return listar();
	}
	
	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteDTO> listarApenasNomes() { 
		return listar();
	}
	*/
	
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
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplosRestaurantes(@RequestBody List<Long> restaurantesIds) {
		try {
			service.ativarVariosRestaurantes(restaurantesIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplosRestuarantes(@RequestBody List<Long> restaurantesIds) {
		try {
			service.inativarVariosRestaurantes(restaurantesIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
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
