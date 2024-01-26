package com.luan.algafoodapi.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

	CRIADO ("Criado"),
	CONFIRMADO ("Confirmado", CRIADO),// add restrição para ir apenas do CRIADO para CONFIRMADO
	ENTREGUE ("Entregue", CONFIRMADO),// add restrição para ir apenas do CONFIRMADO para ENTREGUE
	CANCELADO ("Cancelado", CRIADO, CONFIRMADO);// add restrição para ir apenas do CRIADO para CANCELADO
	
	private String descricao;
	private List<StatusPedido> statusAnteriores;
	
	StatusPedido(String descricao, StatusPedido...statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}
	
}
