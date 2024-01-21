package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.luan.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.luan.algafoodapi.domain.exception.FormaPagamentoNaoEncontradaException;
import com.luan.algafoodapi.domain.model.FormaPagamento;
import com.luan.algafoodapi.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return repository.save(formaPagamento);
	}

	public FormaPagamento buscaOuFalha(Long formaPagamentoId) {
		return repository.findById(formaPagamentoId).orElseThrow(() -> new EstadoNaoEncontradaException(
				String.format("Não foi encotrado nenhuma forma de pagamento com id: ", formaPagamentoId)));
	}

	@Transactional
	public void excluir(Long formaPagamentoId) {
		try {
			repository.deleteById(formaPagamentoId);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(String.format("Não foi possível excluir a forma de pagamento com id %d, pois o id informado não se encontra em nosso sistema", formaPagamentoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Forma de pagamento de código %d não pode ser removido, pois está em uso.", formaPagamentoId));
		}
	}
	
}
