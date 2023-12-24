package com.luan.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.luan.algafoodapi.domain.model.Estado;
import com.luan.algafoodapi.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public Estado buscaOuFalha(Long estadoId) {
		return repository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradaException(
				String.format("Não foi encontrado estado com id ", estadoId)));
	}

	public Estado salvar(Estado estado) {
		return repository.save(estado);
	}

	public void excluir(Long estadoId) {
		try {
			repository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(String.format("Não foi possivel remover o estado, pois o Id: %d informado não se encontra em nosso siterma", estadoId));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Estado de código %d não pode ser removido, pois está em uso ", estadoId));
		}
		
	}
	
}
