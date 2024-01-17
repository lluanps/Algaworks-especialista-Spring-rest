package com.luan.algafoodapi.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public UsuarioNaoEncontradoException(Long usuarioId) {
		this(String.format("Não existe um cadastro de usuario com id %d.", usuarioId));
	} 
	
}
