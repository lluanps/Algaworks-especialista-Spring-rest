package com.luan.algafoodapi.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Espetinho de Cupim")
	private String nome;
	
	@ApiModelProperty(example = "Acompanha farinha, mandioca e vinagret")
	private String descricao;
	
	@ApiModelProperty(example = "12.50")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true")
	private boolean ativo;
	
}
