package com.luan.algafoodapi.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.luan.algafoodapi.api.model.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaDTO {
	
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
