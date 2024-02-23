package com.luan.algafoodapi.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

	@ApiModelProperty(example = "2")
	private Long id;
	
}
