package com.luan.algafoodapi.api.model.mixin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luan.algafoodapi.domain.model.Cozinha;
import com.luan.algafoodapi.domain.model.Endereco;
import com.luan.algafoodapi.domain.model.FormaPagamento;
import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.model.Produto;

public class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	private List<Produto> produto;
	
	@JsonIgnore
	private List<Pedido> pedido;
	
}
