package com.luan.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.luan.algafoodapi.api.model.input.RestauranteInputDTO;
import com.luan.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Cozinha;
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
	
	@GetMapping("/{id}")
	public RestauranteDTO buscarRestaurantePorId(@PathVariable Long id) {
		Restaurante restaurante = service.buscaOuFalha(id);
		return dtoAssembler.toModel(restaurante);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDTO save(@RequestBody @Valid RestauranteInputDTO restauranteInput) {

		try {
			Restaurante restaurante = inputDisassembler.toDomainObjetct(restauranteInput);
			
			return dtoAssembler.toModel(service.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@PathVariable @Valid Long restauranteId,
	        @RequestBody RestauranteInputDTO restauranteInput) {
		Restaurante restaurante = inputDisassembler.toDomainObjetct(restauranteInput);
	    Restaurante restauranteAtual = service.buscaOuFalha(restauranteId);
	    
	    BeanUtils.copyProperties(restaurante, restauranteAtual,
	            "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
	    
	    try {
	        return dtoAssembler.toModel(service.salvar(restauranteAtual));
	    } catch (EntidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage());
	    }
	}
	
}
