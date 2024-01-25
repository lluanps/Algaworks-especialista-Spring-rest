package com.luan.algafoodapi.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.exception.NegocioException;
import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	@Autowired
	PedidoService pedidoService;
	
	private static String MSG_DE_ERRO_AO_ALTERAR_STATUS_DO_PEDIDO = "Status do pedido %s não pode ser alterado de %s para %s";
	
	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		
		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(
					String.format(MSG_DE_ERRO_AO_ALTERAR_STATUS_DO_PEDIDO,
							pedido.getId(),
							pedido.getStatus().getDescricao(),
							StatusPedido.CONFIRMADO.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		
		if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
			throw new NegocioException(
					String.format(MSG_DE_ERRO_AO_ALTERAR_STATUS_DO_PEDIDO,
							pedido.getId(),
							pedido.getStatus().getDescricao(),
							StatusPedido.ENTREGUE.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido.setDataEntrega(OffsetDateTime.now());
	}

	@Transactional 
	public void cancelar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		
		if (!pedido.getStatus().equals(StatusPedido.ENTREGUE)) {
			throw new NegocioException(
					String.format(MSG_DE_ERRO_AO_ALTERAR_STATUS_DO_PEDIDO,
							pedido.getId(),
							pedido.getStatus().getDescricao(),
							StatusPedido.CANCELADO.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());
	}
	
}
