package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		pedido.confirmar();
		
		pedidoRepository.save(pedido);//usado para funcionar o evento
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
		
		pedidoRepository.save(pedido);
	}
	
}
