package com.luan.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	NEGOCIO_EXCEPETION("/negocio-exception", "Negócio exception"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso");
	
	private String title;
	private String uri;
	
	private ApiErrorType(String title, String path) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

}
