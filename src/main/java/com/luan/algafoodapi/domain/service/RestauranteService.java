package com.luan.algafoodapi.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	public List<Restaurante> findAll() {
		return repository.findAll();
	}
	
	public Optional<Restaurante> findById(Long restaurantedId) {
		try {
			return repository.findById(restaurantedId);
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(
					String.format("Restaurante id %d, não existe", restaurantedId));
		}
	}
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscaOuFalha(cozinhaId);
		
		restaurante.setCozinha(cozinha);
		restaurante.setDataCadastro(OffsetDateTime.now());
		
		return repository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long restaurantedId) {
		Restaurante restauranteAtual = buscaOuFalha(restaurantedId);
		restauranteAtual.ativar();
	}

	@Transactional
	public void inativar(Long restaurantedId) {
		Restaurante resturanteAtual = buscaOuFalha(restaurantedId);
		resturanteAtual.inativar();
	}
	
	public Restaurante buscaOuFalha(Long restauranteId) {
		return repository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(
				String.format("Não existe um restaurante com esse id %d", restauranteId)));
	}
	
}
