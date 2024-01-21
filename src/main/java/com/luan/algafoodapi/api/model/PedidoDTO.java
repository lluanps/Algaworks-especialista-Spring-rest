package com.luan.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private RestauranteResumoDTO restaurantes;
	private UsuarioResumoDTO cliente;
	private FormaPagamentoDTO formaPagamento;
	private EnderecoDTO endereco;
	private List<ItemProutoDTO> itemPedidos;
	
}
