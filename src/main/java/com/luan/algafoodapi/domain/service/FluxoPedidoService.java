package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.Pedido;

@Service
public class FluxoPedidoService {

	@Autowired
	PedidoService pedidoService;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		pedido.confirmar();
	}

	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		pedido.entregar();
	}

	@Transactional 
	public void cancelar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		pedido.cancelar();
	}
	
}
