package com.luan.algafoodapi.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Florianópolis")
	private String nome;
	
	@ApiModelProperty(example = "Santa Catarina")
	private String estado;
	
}
