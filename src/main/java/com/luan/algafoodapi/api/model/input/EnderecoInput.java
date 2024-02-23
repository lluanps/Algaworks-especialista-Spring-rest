package com.luan.algafoodapi.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	@ApiModelProperty(example = "88060-000")
	@NotBlank
	private String cep;

	@ApiModelProperty(example = "Rua Floripa")
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "000")
	@NotBlank
	private String numero ;
	
	@ApiModelProperty(example = "Casa")
	private String complemento;
	
	@ApiModelProperty(example = "Spring")
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
