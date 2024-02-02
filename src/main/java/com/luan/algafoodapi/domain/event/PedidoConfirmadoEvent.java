package com.luan.algafoodapi.domain.event;

import com.luan.algafoodapi.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//é uma boa pratica utilizar nome no passado ao criar classe referente ao Event
public class PedidoConfirmadoEvent {

	private Pedido pedido;
	
}
