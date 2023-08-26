package com.luan.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.Restaurante;
import com.luan.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository repository;

	public List<Restaurante> findAll() {
		return repository.listar();
	}
	
	public Restaurante findById(Long id) {
		return repository.buscar(id);
	}


}
