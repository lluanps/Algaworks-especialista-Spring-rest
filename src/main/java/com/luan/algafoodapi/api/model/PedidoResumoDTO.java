package com.luan.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDTO {

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoDTO restaurantes;
	private UsuarioResumoDTO cliente;
	
}
