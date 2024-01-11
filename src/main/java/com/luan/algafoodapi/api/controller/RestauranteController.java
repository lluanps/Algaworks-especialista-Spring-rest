package com.luan.algafoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luan.algafoodapi.api.model.CozinhaDTO;
import com.luan.algafoodapi.api.model.RestauranteDTO;
import com.luan.algafoodapi.api.model.input.RestauranteInputDTO;
import com.luan.algafoodapi.core.validation.ValidacaoException;
import com.luan.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;
import com.luan.algafoodapi.domain.service.CozinhaService;
import com.luan.algafoodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService service;
	
	@Autowired
	private RestauranteRepository repository;
	
	@GetMapping
	public List<RestauranteDTO> findAll() { 
		return toCollectionDto(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public RestauranteDTO buscarRestaurantePorId(@PathVariable Long id) {
		Restaurante restaurante = service.buscaOuFalha(id);
		return toModel(restaurante);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDTO save(@RequestBody @Valid RestauranteInputDTO restauranteInput) {

		try {
			Restaurante restaurante = toDomainObjetct(restauranteInput);
			
			return toModel(service.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@PathVariable @Valid Long restauranteId,
	        @RequestBody RestauranteInputDTO restauranteInput) {
		Restaurante restaurante = toDomainObjetct(restauranteInput);
	    Restaurante restauranteAtual = service.buscaOuFalha(restauranteId);
	    
	    BeanUtils.copyProperties(restaurante, restauranteAtual, 
	            "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
	    
	    try {
	        return toModel(service.salvar(restauranteAtual));
	    } catch (EntidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage());
	    }
	}
	
	private RestauranteDTO toModel(Restaurante restaurante) {
		RestauranteDTO dto = new RestauranteDTO();
		
		CozinhaDTO cozinhaDto = new CozinhaDTO();
		cozinhaDto.setId(restaurante.getCozinha().getId());
		cozinhaDto.setNome(restaurante.getCozinha().getNome());
		
		dto.setId(restaurante.getId());
		dto.setNome(restaurante.getNome());
		dto.setTaxaFrete(restaurante.getTaxaFrete());
		dto.setCozinha(cozinhaDto);
		return dto;
	}
	
	private List<RestauranteDTO> toCollectionDto(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
    
	private Restaurante toDomainObjetct(RestauranteInputDTO restauranteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
	
}
