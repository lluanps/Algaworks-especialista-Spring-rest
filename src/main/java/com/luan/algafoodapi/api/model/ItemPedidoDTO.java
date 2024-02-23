package com.luan.algafoodapi.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {
	
	@ApiModelProperty(example = "7")
	private Long produtoId;
	
	@ApiModelProperty(example = "Bife Ancho")
	private String produtoNome;
	
	@ApiModelProperty(example = "4")
	private Integer quantidade;
	
	@ApiModelProperty(example = "89.00")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "316.00")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Sem Pímenta")
	private String observacao;

}
