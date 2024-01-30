package com.luan.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.buscaOuFalha(pedidoId);
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("O pedido de código <strong>" 
						+ pedido.getId() + "</strong> foi confirmado!")
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
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
