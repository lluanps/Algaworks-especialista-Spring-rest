package com.luan.algafoodapi.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoDTO {
	
	@ApiModelProperty(example = "4")
	private Long id;
	
	@ApiModelProperty("Java Steakhouse")
	private String nome;

}
