package com.luan.algafoodapi.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@ApiModelProperty(example = "lluanps")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "lluanps@gmail.com")
	@NotBlank
	@Email
	private String email;

}
