package com.luan.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luan.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();// descarrega todas as mudancas pendendtes do banco de dados
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removido, pois esta em uso", id));
		}
	}
	
	public Cozinha buscaOuFalha(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}
	
}
