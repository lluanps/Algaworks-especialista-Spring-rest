package com.luan.algafoodapi.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

	@ApiModelProperty(example = "88060-000")
	private String cep;
	
	@ApiModelProperty(example = "Rua Floripa")
	private String logradouro;
	
	@ApiModelProperty(example = "000")
	private String numero ;
	
	@ApiModelProperty(example = "Casa")
	private String complemento;

	@ApiModelProperty(example = "Spring")
	private String bairro;
	
	private CidadeResumoDTO cidade;
	
}
