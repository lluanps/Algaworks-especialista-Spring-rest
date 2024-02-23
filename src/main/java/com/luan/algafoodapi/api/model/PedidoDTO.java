package com.luan.algafoodapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "316.00")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "7.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "323.00")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CRIADO")
	private String status;
	
	@ApiModelProperty(example = "2024-02-23T14:52:46Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "2024-02-23T14:55:26Z")
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "2024-02-23T15:12:06Z")
	private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(example = "2024-02-23T14:53:26Z")
	private OffsetDateTime dataCancelamento;
	
	private RestauranteResumoDTO restaurante;
	private UsuarioDTO cliente;
	private FormaPagamentoDTO formaPagamento;
	private EnderecoDTO enderecoEntrega;
	private List<ItemPedidoDTO> itens;
	
}
