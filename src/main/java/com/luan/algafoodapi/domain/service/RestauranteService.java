package com.luan.algafoodapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.CozinhaRepository;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public List<Restaurante> findAll() {
		return repository.findAll();
	}
	
	public Optional<Restaurante> findById(Long id) {
		try {
			return repository.findById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Restaurante id %d, não existe", id));
		}
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() ->new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return repository.save(restaurante);
	}
	
	public Restaurante buscaOuFalha(Long restauranteId) {
		return repository.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe um restaurante com esse id %d", restauranteId)));
	}
	

}
