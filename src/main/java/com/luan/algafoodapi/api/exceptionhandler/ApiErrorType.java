package com.luan.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	NEGOCIO_EXCEPETION("/negocio-exception", "Violação de regra de negócio"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel");
	
	private String title;
	private String uri;
	
	private ApiErrorType(String title, String path) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

}
