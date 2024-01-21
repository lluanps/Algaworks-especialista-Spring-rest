package com.luan.algafoodapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public PedidoNaoEncontradoException(Long pedidoId) {
		this(String.format("Não existe um pedido com código %s.", pedidoId));
	}

}
