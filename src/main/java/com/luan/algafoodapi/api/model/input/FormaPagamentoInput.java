package com.luan.algafoodapi.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

	@ApiModelProperty(example = "Bitcoin")
	@NotBlank
	private String descricao;
	
}
