package com.luan.algafoodapi.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

	@NotNull
	private Long id;
	
	@NotBlank
	private String nome;
	
}
