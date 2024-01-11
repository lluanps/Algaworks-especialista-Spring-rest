package com.luan.algafoodapi.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.model.Endereco;
import com.luan.algafoodapi.domain.model.FormaPagamento;
import com.luan.algafoodapi.domain.model.Produto;

public class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;

//	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
//	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento;
	
	@JsonIgnore
	private List<Produto> produto;
	
}
