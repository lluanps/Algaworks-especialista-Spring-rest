package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.EstadoNaoEncontradaException;
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
	
}
