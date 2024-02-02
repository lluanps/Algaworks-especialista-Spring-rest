package com.luan.algafoodapi.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.luan.algafoodapi.domain.event.PedidoConfirmadoEvent;
import com.luan.algafoodapi.domain.model.Pedido;
import com.luan.algafoodapi.domain.service.EnvioEmailService;
import com.luan.algafoodapi.domain.service.EnvioEmailService.Mensagem;

//para criar um novo evento para adicionar uma nova regra, basta criar uma classe semelhante a essa q o evento sera disparado
@Component
public class NotificacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioEmail;

//	@EventListener
	@TransactionalEventListener//usado para atualizar o banco de dados antes e depois enviar email caso obtenha sucesso
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
	}

}
