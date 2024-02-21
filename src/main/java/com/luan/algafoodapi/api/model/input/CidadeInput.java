package com.luan.algafoodapi.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
	
	@ApiModelProperty(example = "Florianópolis")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "1")
	@Valid
	@NotNull
	private EstadoIdInput estado;

}
