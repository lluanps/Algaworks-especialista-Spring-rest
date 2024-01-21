package com.luan.algafoodapi.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String msg) {
		super(msg);
	}

	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Não existe nenhum cadastro de permissao com o código %s.", permissaoId));
	}
	
}
