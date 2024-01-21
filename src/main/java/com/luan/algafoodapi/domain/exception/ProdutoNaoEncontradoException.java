package com.luan.algafoodapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
		this(String.format("Não existe um cadastro de produto com o id &d no restaurante com o id %d", produtoId, restauranteId));
	}
	
}
