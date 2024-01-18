package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.ProdutoNaoEncontradoException;
import com.luan.algafoodapi.domain.model.Produto;
import com.luan.algafoodapi.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	public Produto buscaOuFalha(Long restauranteId, Long produtoId) {
		return repository.findProdutoIdByRestauaranteId(restauranteId, produtoId)
			.orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

}
